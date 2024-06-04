<template>
  <div class="container">
      <h4>게시글 상세</h4>
      <hr />
      <div class="d-flex justify-content-center">
          <div class="card" style="width: 30rem">
              <div class="card-body">
                  <div class="mb-3 d-flex justify-content-between align-items-center">
                      <h4 class="card-title">{{ store.board.title }} <span class="badge bg-danger">{{ store.board.viewCnt
                      }}</span></h4>
                      <div class="d-flex justify-content-end">
                          <h6 class="card-subtitle mx-3 text-body-secondary">{{ store.board.writer }}</h6>
                          <h6 class="card-subtitle text-body-secondary">{{ store.board.regDate }}</h6>
                      </div>
                  </div>
                  <p class="card-text ">
                      {{ store.board.content }}
                  </p>
                  <div class="d-flex justify-content-center">
                      <button class="mx-3 btn btn-outline-success" @click="moveUpdate">수정</button>
                      <button class="mx-3 btn btn-outline-danger" @click="deleteBoard">삭제</button>
                  </div>
              </div>
          </div>
      </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/board";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const store = useBoardStore();

onMounted(() => {
  store.getBoard(route.params.id);
});

const moveUpdate = function () {
  router.push({ name: "boardUpdate" });
};

const deleteBoard = function () {
  axios
      .delete(`http://localhost:8080/board/${route.params.id}`)
      .then(() => {
          router.push({ name: "boardList" });
      })
      .catch(() => { });
};
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
}

hr {
margin-bottom: 20px;
}

.card {
border: none;
border-radius: 10px;
box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
background-color: #ffffff;
}

.card-title {
font-weight: bold;
color: #343a40;
font-size: 1.25rem;
}

.badge {
font-size: 1rem;
padding: 0.5em 0.75em;
}

.card-subtitle {
font-size: 0.9rem;
color: #6c757d;
}

.card-text {
margin: 1.5rem 0;
font-size: 1rem;
line-height: 1.6;
color: #495057;
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
</style>
