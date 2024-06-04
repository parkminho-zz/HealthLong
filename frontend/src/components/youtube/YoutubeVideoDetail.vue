<template>
  <div v-if="store.selectedVideo">
    <h4>영상상세보기</h4>
    <iframe
      width="560"
      height="315"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen
    ></iframe>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { computed, onMounted } from 'vue';

const store = useYoutubeStore();

const videoURL = computed(() => {
  const videoId = store.selectedVideo?.id.videoId;
  return videoId ? `https://www.youtube.com/embed/${videoId}` : '';
});

onMounted(() => {
  store.selectedVideo = null; // 초기화
});
</script>

<style scoped></style>
