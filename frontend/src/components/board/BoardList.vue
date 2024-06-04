<template>
  <div class="container">
    <h4>자유 게시판</h4>
    <hr />
    <BoardSearchInput />
    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>조회수</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="board in currentPageBoardList" :key="board.id">
          <td>{{ board.id }}</td>
          <td>
            <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
          </td>
          <td>{{ board.writer }}</td>
          <td>{{ board.viewCnt }}</td>
          <td>{{ board.regDate }}</td>
        </tr>
      </tbody>
    </table>
    <div class="pagination-container">
      <button
        class="page-button"
        @click="currentPage--"
        :disabled="currentPage <= 1"
      >
        &lt;
      </button>
      <button
        class="page-button"
        v-for="page in pageCount"
        :key="page"
        @click="clickPage(page)"
        :class="{ active: currentPage === page }"
      >
        {{ page }}
      </button>
      <button
        class="page-button"
        @click="currentPage++"
        :disabled="currentPage >= pageCount"
      >
        &gt;
      </button>
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from '@/stores/board';
import { computed, onMounted, ref } from 'vue';
import BoardSearchInput from './BoardSearchInput.vue';

const store = useBoardStore();
onMounted(() => {
  store.getBoardList();
});

const perPage = 7;
const currentPage = ref(1);

const pageCount = computed(() => {
  return Math.ceil(store.boardList.length / perPage);
});

const clickPage = (page) => {
  currentPage.value = page;
};

const currentPageBoardList = computed(() => {
  return store.boardList.slice(
    (currentPage.value - 1) * perPage,
    currentPage.value * perPage
  );
});
</script>

<style scoped>
.container {
  margin: 40px auto;
  max-width: 1200px;
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

.table {
  margin-bottom: 20px;
  width: 100%;
  border-collapse: collapse;
}

.table th,
.table td {
  vertical-align: middle;
  padding: 12px;
  border: 1px solid #dee2e6;
}

.table th {
  background-color: #343a40;
  color: white;
  font-weight: bold;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1;
}

.table a {
  color: #3498db;
  text-decoration: none;
}

.table a:hover {
  text-decoration: underline;
}

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.page-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 15px;
  margin: 0 5px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 1rem;
}

.page-button.active {
  background-color: #2c6aa6;
}

.page-button:disabled {
  background-color: #b0c4de;
  cursor: not-allowed;
}
</style>
