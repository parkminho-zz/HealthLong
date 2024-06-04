<template>
    <div class="container">
        <h4>게시글 수정</h4>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="제목" v-model="store.board.title">
            <label for="title"></label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="writer" placeholder="작성자" readonly v-model="store.board.writer">
            <label for="writer"></label>
        </div>
        <div class="form-floating mb-3">
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                v-model="store.board.content"></textarea>
            <label for="content"></label>
        </div>
        <div class="d-flex justify-content-end">
            <button class="btn btn-outline-success mx-3" @click="updateBoard">수정</button>
            <button class="btn btn-outline-danger" @click="backButton">뒤로</button>
        </div>
    </div>
</template>
  
<script setup>
import { useBoardStore } from '@/stores/board';
import { useRouter } from 'vue-router';
const store = useBoardStore()
const router = useRouter()

const updateBoard = function () {
    store.updateBoard()
}
const backButton = function () {
    router.go(-1);
    // router.back();
}
</script>
  
<style scoped>
.container {
  margin: 40px auto;
  max-width: 800px;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h4 {
  color: #343a40;
  font-weight: bold;
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.form-floating {
  margin-bottom: 20px;
}

.form-control {
  border-radius: 5px;
  padding: 10px;
  font-size: 1rem;
}

.form-control:focus {
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
  border-color: #80bdff;
}

label {
  font-size: 1rem;
  color: #6c757d;
}

.btn {
  width: 100px;
  border-radius: 5px;
}

.btn-outline-success {
  border-color: #28a745;
  color: #28a745;
}

.btn-outline-success:hover {
  background-color: #28a745;
  color: white;
}

.btn-outline-danger {
  border-color: #dc3545;
  color: #dc3545;
}

.btn-outline-danger:hover {
  background-color: #dc3545;
  color: white;
}

.d-flex {
  margin-top: 20px;
}
</style>
