<template>
  <div>
    <h2>확인하고 싶은 영상을 선택하세요</h2>
    <button class="exercise-btn" @click="search('가슴 운동')">가슴 운동</button>
    <button class="exercise-btn" @click="search('어깨 운동')">어깨 운동</button>
    <button class="exercise-btn" @click="search('하체 운동')">하체 운동</button>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';

const store = useYoutubeStore();

const search = function (keyword) {
  store.youtubeSearch(keyword);
};
</script>

<style scoped>
.exercise-btn {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  margin: 5px;
  cursor: pointer;
  transition: background-color 0.3s, box-shadow 0.3s;
}

.exercise-btn:hover {
  background-color: #0056b3;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
