<template>
  <div class="page-container">
      <RouterView>

      </RouterView>
  </div>
</template>

<script setup>
    import { onMounted} from 'vue';
  import UserMyInfo from '@/components/user/UserMyInfo.vue';
  let isSession = false
onMounted(() => {
    if (!sessionStorage.getItem("access-token")) {
    isSession = false;
    window.location.href = '/';
  }
})
</script>

<style scoped>
.page-container {
  position: relative;
  padding-top: 90px; /* 헤더 높이만큼 패딩을 추가하여 내용이 헤더 아래에서 시작되도록 설정 */
  /* text-align: center; */
}
</style>