<template>
  <div class="page-container">
    <div class="ticket-container">
      <h2>시간권 선택</h2>
      <div v-for="(ticket, index) in tickets" :key="index" class="ticket">
        <div class="ticket-info">
          <span class="ticket-name">{{ ticket.name }}</span>
          <span class="ticket-price">{{ ticket.price }}원</span>
          <div class="controls">
            <button @click="decrement(index)" class="control-btn">-</button>
            <span class="quantity">{{ ticket.quantity }}</span>
            <button @click="increment(index)" class="control-btn">+</button>
          </div>
        </div>
      </div>
      <div class="total-amount">총 결제 금액: {{ totalAmount }}원</div>
      <button class="checkout-button" @click="checkout">결제하기</button>
    </div>
  </div>
  <Payment v-if="showComponent" :totalHours="totalHours" :totalPrice="totalAmount"/>

</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import Payment from '@/components/pay/Payment.vue';
import { usePaymentStore } from '@/stores/payment';

let isSession = false

const store = usePaymentStore()

const router = useRouter()
const route = useRoute()

onMounted(() => {
    if (!sessionStorage.getItem("access-token")) {
    isSession = false;
    window.location.href = '/';
  }
})

const registUser = function () {
  store.payrequest()
};

let showComponent =ref(false) 

const tickets = ref([
  { name: '1시간권', price: 10000, quantity: 0 },
  { name: '2시간권', price: 17000, quantity: 0 },
  { name: '5시간권', price: 42000, quantity: 0 },
  { name: '10시간권', price: 80000, quantity: 0 },
]);

const increment = (index) => {
  tickets.value[index].quantity++;
};

const decrement = (index) => {
  if (tickets.value[index].quantity > 0) {
    tickets.value[index].quantity--;
  }
};

const totalAmount = computed(() => {
  return tickets.value.reduce((total, ticket) => {
    return total + (ticket.quantity * ticket.price);
  }, 0);
});

const totalHours = computed(() => {
  return tickets.value.reduce((total, ticket) => {
    return total + (ticket.quantity * parseInt(ticket.name) * 60);
  }, 0);
});

const checkout = () => {
  const selectedTickets = tickets.value.filter(ticket => ticket.quantity > 0);
  if (selectedTickets.length > 0) {
    alert(`결제할 티켓: ${selectedTickets.map(ticket => `${ticket.name} ${ticket.quantity}개`).join(', ')}`);
    // 실제 결제 처리 로직을 여기에 추가
    console.log(totalHours.value)
    showComponent.value = true;
  } else {
    alert('선택된 티켓이 없습니다.');
  }
};
</script>

<style scoped>
.page-container {
  position: relative;
  padding-top: 90px;
  text-align: center;
  background-color: #f7f7f7;
  min-height: 100vh;
}

h1 {
  font-size: 2.5em;
  color: #333;
}

.ticket-container {
  margin-top: 20px;
}

.ticket {
  background-color: #fff;
  border: 2px dashed #ccc;
  border-radius: 10px;
  padding: 20px;
  margin: 10px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.ticket-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.ticket-name {
  font-size: 1.2em;
  font-weight: bold;
  color: #555;
  flex: 1;
}

.ticket-price {
  font-size: 1em;
  color: #777;
  margin-left: 10px;
}

.controls {
  display: flex;
  align-items: center;
}

.control-btn {
  width: 30px;
  height: 30px;
  font-size: 18px;
  border: none;
  background-color: #ddd;
  color: #333;
  cursor: pointer;
  border-radius: 5px;
  margin: 0 5px;
  transition: background-color 0.3s;
}

.control-btn:hover {
  background-color: #bbb;
}

.quantity {
  font-size: 1.2em;
  width: 40px;
  text-align: center;
}

.total-amount {
  font-size: 1.2em;
  font-weight: bold;
  margin-top: 20px;
}

.checkout-button {
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007BFF;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.checkout-button:hover {
  background-color: #0056b3;
}
</style>
