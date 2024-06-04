<template>
  <div class="header">
    <div class="logo-container" v-if="isSession == true" >
      
      <RouterLink :to="{ name: 'mainView' }">
        <img src="@/assets/logo.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
    <div class="logo-container2" v-if="isSession == false">
      
      <RouterLink :to="{ name: 'introduceView' }">
        <img src="@/assets/logo.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
    <div class="title-container">
      <div class="title">
        <h3 v-if="name"> {{ name }}님 헬롱~</h3>
        <h3 v-else></h3>      
      </div>
      <div class="logout" v-if="name">
        <button class="btn btn-outline-danger" @click="logout">로그아웃</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/board";
const route = useRoute();
const router = useRouter();
const store = useBoardStore();



let name = ref('') 
let isSession = false

onMounted(() => {
  name.value = sessionStorage.getItem('name')
  if (sessionStorage.getItem("access-token")) {
  // 세션에 access-token이 존재하는 경우
  isSession = true;
} else {
  // 세션에 access-token이 존재하지 않는 경우
  isSession = false;
}

});

const logout = () => {
  sessionStorage.clear();
  name.value = '';
  window.location.href = '/';
};

</script>

<style scoped>
/* 헤더의 CSS */

.header {
  position: fixed; /* 헤더를 고정 */
  top: 0;
  width: 100%;
  background-color: white;
  padding: 10px 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 1000; /* 다른 요소 위에 표시되도록 높은 값으로 설정 */
  display: flex; /* Flexbox를 사용하여 요소들을 수평으로 배치 */
  align-items: center; /* Flexbox 내의 요소들을 수직 중앙 정렬 */
  justify-content: space-between; 
}
.logo-container {
  margin-right: 20px; /* 로고와 텍스트 사이의 간격 조절 */
  display: flex;
  justify-content: flex-start; /* 기본 상태에서 왼쪽 정렬 */
}

.logo-container2 {
  margin-left: 550px; /* 로고와 텍스트 사이의 간격 조절 */
  display: flex;
  justify-content: center; /* 비로그인 상태일 때 중앙 정렬 */
  flex-grow: 1; /* 로고가 중앙에 오도록 */
  
}

.logo {
  width: 40px;
  height: auto;
}

.title-container {
  flex-grow: 1; /* 제목 텍스트가 남은 공간을 모두 차지하도록 설정 */
  display: flex; /* Flexbox를 사용하여 요소들을 수평으로 배치 */
  justify-content: space-between; /* 요소들을 좌우로 배치 */
  align-items: center; /* Flexbox 내의 요소들을 수직 중앙 정렬 */
}

.title {
  margin: 0;
}

.logout {
  padding-right: 3%; /* 로그아웃 버튼을 오른쪽 끝으로 배치 */
}

.btn {
  height: 40px;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 10px;
  padding: 0 15px;
}

.btn-outline-danger {
  color: #dc3545;
  border-color: #dc3545;
}

.btn-outline-danger:hover {
  background-color: #dc3545;
  color: white;
}
</style>