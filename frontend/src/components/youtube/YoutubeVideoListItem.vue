<template>
  <li class="video-list-item" @click="clickVideo">
    <img :src="video.snippet.thumbnails.high.url" alt="video thumbnail" class="video-thumbnail" />
    <div class="video-content">
      <h5 class="video-title">{{ video.snippet.title }}</h5>
      <p class="video-description">{{ video.snippet.description }}</p>
    </div>
  </li>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true
  }
});

const clickVideo = () => {
  store.clickVideo(props.video);
};
</script>

<style scoped>
.video-list-item {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: box-shadow 0.3s ease;
  width: calc(50% - 10px); /* 2개의 항목이 한 줄에 표시되도록 설정 */
  margin-bottom: 20px;
  overflow: hidden;
  cursor: pointer;
  display: flex;
  flex-direction: column;
}

.video-list-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.16), 0 4px 8px rgba(0, 0, 0, 0.23);
}

.video-thumbnail {
  width: 100%;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
}

.video-content {
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 1;
}

.video-title {
  font-size: 1.1rem;
  color: #333;
  margin: 0 0 10px;
  font-weight: bold;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.video-description {
  font-size: 0.9rem;
  color: #666;
  margin: 0;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
</style>
