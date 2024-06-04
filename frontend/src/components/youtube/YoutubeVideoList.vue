<template>
  <div class="search-results">
    <h4 class="title">검색 결과</h4>
    <ul class="video-list">
      <YoutubeVideoListItem
        v-for="video in store.videos"
        :key="video.id.videoId"
        :video="video"
      ></YoutubeVideoListItem>
    </ul>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore();
</script>

<style scoped>
.search-results {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 800px; /* 중앙 정렬을 위한 최대 너비 설정 */
  margin: auto; /* 중앙 정렬 */
}

.title {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

.video-list {
  list-style: none;
  padding: 0;
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: center;
}

.video-list-item {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: box-shadow 0.3s ease;
  width: calc(50% - 10px); /* 2개의 항목이 한 줄에 표시되도록 설정 */
  margin-bottom: 20px;
  overflow: hidden;
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
