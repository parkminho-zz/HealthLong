<template>
  <div class="page-container">
    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th>주문번호</th>
          <th>가격</th>
          <th>결제일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="payment in sortedPaymentList" :key="payment.impUid">
          <td>{{ payment.impUid }}</td>
          <td>{{ payment.paidAmount }}</td>
          <td>{{ payment.payDay }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { computed, onMounted } from 'vue';

const store = useUserStore();
let isSession = false

onMounted(() => {
  if (!sessionStorage.getItem("access-token")) {
    isSession = false;
    window.location.href = '/';
  }
  store.getPaymentList();
});

// 결제 리스트를 최신 결제일 순으로 정렬하는 computed 속성 추가
const sortedPaymentList = computed(() => {
  return store.paymentList.slice().sort((a, b) => new Date(b.payDay) - new Date(a.payDay));
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
</style>
