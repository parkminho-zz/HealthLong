<template>
  <div @scroll="handleScroll" class="page-container">
    
    <!-- 이미지가 스크롤되면서 표시되는 섹션 -->
    <div class="scroll-container">
      <img src="@/assets/intro1.png" alt="Image 1" class="full-image">
      <img src="@/assets/intro2.png" alt="Image 2" class="full-image">
      <img src="@/assets/intro3.png" alt="Image 3" class="full-image">
      <img src="@/assets/intro4.png" alt="Image 3" class="full-image">
    </div>
    
    <!-- 로그인 버튼 -->
    <div :class="['login-button', { 'scrolled': scrolled }]">
      <router-link :to="{ name: 'loginView' }">
        <button class="btn btn-outline-primary">로그인</button>
      </router-link>    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { RouterLink } from 'vue-router';

let isSession = false

  onMounted(() => {
    if (sessionStorage.getItem("access-token")) {
    // 세션에 access-token이 존재하는 경우
    isSession = true;
    window.location.href = '/main';
  } else {
    // 세션에 access-token이 존재하지 않는 경우
    isSession = false;
  }
  
  })

const scrolled = ref(false);

const handleScroll = (event) => {
  const target = event.target;
  scrolled.value = target.scrollTop > 0;
};
</script>


<style scoped>
.page-container {
  position: relative;
  height: 100vh;
  overflow-y: auto;
}

.scroll-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 60px; /* 네비게이션 바 높이 */
}

.full-image {
  width: 100%;
  height: auto;
  object-fit: cover; /* 이미지가 부모 컨테이너에 맞게 조절됨 */
  margin-bottom: 20px; /* 이미지 간격 */
}

.login-button {
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  transition: all 0.5s ease; /* 자연스러운 애니메이션을 위한 속도 조절 */
}

.login-button.scrolled {
  width: 90vw; /* 화면 너비의 90%를 차지하도록 설정 */
  max-width: 600px; /* 최대 너비 */
  left: 50%;
  transform: translate(-50%, -50%) scale(1.1); /* 중앙에서 커지도록 조정 */
  border-radius: 10px;
  text-align: center;
}

.btn-outline-primary {
  color: #007bff;
  border-color: #007bff;
  font-size: 1.2rem;
  padding: 10px 20px;
  border-radius: 10px;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%; /* 버튼이 부모 요소의 넓이에 맞춰지도록 설정 */
  height: 100%; /* 버튼이 부모 요소의 높이에 맞춰지도록 설정 */
}

.btn-outline-primary:hover {
  background-color: #007bff;
  color: white;
}
</style>
