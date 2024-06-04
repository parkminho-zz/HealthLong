<template>
  <div class="text-center">
    <div class="row align-items-center">
      <div class="col-2">
        <select class="form-select" v-model="searchInfo.key">
          <option value="none">전체</option>
          <option value="writer">작성자</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
        </select>
      </div>
      <div class="col-6">
        <input type="text" class="form-control" v-model="searchInfo.word" />
      </div>
      <div class="col-2">
        <div class="form-check form-check-inline">
          <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="title" value="title" />
          <label class="form-check-label" for="title"><i class="bi bi-fonts">제목 오름차순</i></label>
        </div>
        <div class="form-check form-check-inline">
          <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="writer" value="writer" />
          <label class="form-check-label" for="writer"><i class="bi bi-person-fill">작성자 오름차순</i></label>
        </div>
        <div class="form-check form-check-inline">
          <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="viewCnt" value="view_cnt" />
          <label class="form-check-label" for="viewCnt"><i class="bi bi-eye-fill">조회수 오름차순</i></label>
        </div>
      </div>
      <div class="col-1">
        <div @click="sortChange" class="sort-icon">
          <span v-if="searchInfo.orderByDir === 'asc'">
            <i class="bi bi-arrow-up-right-square-fill"></i>
          </span>
          <span v-else>
            <i class="bi bi-arrow-down-right-square-fill"></i>
          </span>
        </div>
      </div>
      <div class="col-1">
        <button class="btn btn-outline-warning" @click="searchBoardList">검색</button>
      </div>
      <div class="col-1">
        <RouterLink :to="{ name: 'boardWrite' }">
          <button class="btn btn-outline-warning">작성</button>
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";

const store = useBoardStore();
const searchInfo = ref({
  key: "none",
  word: "",
  orderBy: "none",
  orderByDir: "asc",
});
const searchBoardList = function () {
  console.log(searchInfo.value);
  store.searchBoardList(searchInfo.value);
};

const sortChange = function () {
  searchInfo.value.orderByDir = searchInfo.value.orderByDir === "asc" ? "desc" : "asc";
};
</script>

<style scoped>
.text-center {
  margin: 20px 0;
}

.row {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.col-2, .col-6, .col-1 {
  padding: 0 10px;
  padding-left: 2%;
}

.col-1 {
  display: flex;
  justify-content: center;
  width: 70px;
  
}

.form-select {
  width: 100%;
  padding: 8px 12px;
  border-radius: 5px;
  border: 1px solid #ced4da;
  font-size: 1rem;
}

.form-control {
  width: 100%;
  padding: 8px 12px;
  border-radius: 5px;
  border: 1px solid #ced4da;
  font-size: 1rem;
}

.form-check {
  display: flex;
  align-items: center;
  margin-right: 15px;
}

.form-check-label {
  font-size: 0.9rem;
  margin-left: 5px;
  display: flex;
  align-items: center;
}

.form-check-input {
  margin-top: 0.3rem;
  margin-right: 5px;
  appearance: none;
  width: 1rem;
  height: 1rem;
  border: 1px solid #ced4da;
  border-radius: 50%;
  outline: none;
  cursor: pointer;
  transition: background-color 0.2s;
}

.form-check-input:checked {
  background-color: #007bff;
  border-color: #007bff;
}

.form-check-input:checked::before {
  content: '';
  display: block;
  width: 0.5rem;
  height: 0.5rem;
  margin: auto;
  background-color: white;
  border-radius: 50%;
}

.bi {
  margin-right: 5px;
}

.sort-icon {
  font-size: 1.5rem;
  cursor: pointer;
  color: #007bff;
}

.sort-icon:hover {
  color: #0056b3;
}

.btn-outline-warning {
  font-size: 1rem;
  padding: 8px 12px;
  margin: 0 5px;
  width: 60px;
  height: 45px;
}

</style>