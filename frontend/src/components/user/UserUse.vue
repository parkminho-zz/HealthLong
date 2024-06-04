<template>
  <div class="page-container">
    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th>시작시간</th>
          <th>끝시간</th>
          <th>사용 헬스장</th>
          <th>사용 시간</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="useHistory in sortedUseHistoryList" :key="useHistory.ticketUid">
          <td>{{ useHistory.ticketStart }}</td>
          <td>{{ useHistory.ticketEnd }}</td>
          <td>{{ useHistory.gymName }}</td>
          <td>{{ useHistory.ticketUse }} 분</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { computed, onMounted } from 'vue';

const store = useUserStore();
let isSession = false;

onMounted(() => {
  if (!sessionStorage.getItem("access-token")) {
    isSession = false;
    window.location.href = '/';
  }
  store.getUseList();
});

const useHistoryList = computed(() => store.useHistoryList);

const sortedUseHistoryList = computed(() => {
  return [...useHistoryList.value].sort((a, b) => new Date(b.ticketStart) - new Date(a.ticketStart));
});
</script>

<style scoped>
.page-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 12px 15px;
  border: 1px solid #ddd;
}

th {
  background-color: skyblue;
  color: #333;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f1f1f1;
}

td {
  color: #555;
}

thead {
  position: sticky;
  top: 0;
  background-color: #fff;
  z-index: 1;
}
</style>
