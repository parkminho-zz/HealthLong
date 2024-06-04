<template>
    <div class="container">
      <h4 class="title">게시글 작성</h4>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="title" placeholder="제목" v-model="board.title">
        <label for="title">Title</label>
      </div>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="writer" placeholder="작성자" v-model="board.writer">
        <label for="writer">Writer</label>
      </div>
      <div class="form-floating mb-3">
        <textarea class="form-control" id="content" placeholder="내용" style="height: 200px" v-model="board.content"></textarea>
        <label for="content">Content</label>
      </div>
      <div class="d-flex justify-content-end">
        <button class="btn btn-outline-primary" @click="createBoard">등록</button>
      </div>
    </div>
  </template>
    
  <script setup>
  import { ref } from "vue";
  import { useBoardStore } from "@/stores/board";
  
  const board = ref({
    title: "",
    writer: "",
    content: ""
  })
  
  const store = useBoardStore();
  
  const createBoard = function () {
    store.createBoard(board.value)
  };
  </script>
    
  <style scoped>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ced4da;
    border-radius: 10px;
    background-color: #f8f9fa;
  }
  
  .title {
    margin-bottom: 20px;
    color: #3498db; /* 하늘색 */
    text-align: center;
  }
  
  .form-floating {
    margin-bottom: 1rem;
  }
  
  .form-control {
    border-radius: 5px;
    padding: 10px;
    border: 1px solid #ced4da;
    transition: border-color 0.3s, box-shadow 0.3s;
  }
  
  .form-control:focus {
    border-color: #3498db; /* 하늘색 */
    box-shadow: 0 0 0 0.2rem rgba(52, 152, 219, 0.25);
  }
  
  .btn-outline-primary {
    padding: 10px 20px;
    font-size: 1rem;
    border-radius: 5px;
    transition: background-color 0.3s, color 0.3s;
  }
  
  .btn-outline-primary:hover {
    background-color: #3498db; /* 하늘색 */
    color: white;
  }
  </style>
  