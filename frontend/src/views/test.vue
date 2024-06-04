<template>
  <div class="container">
    <h1 class="title">{{ name }}님</h1>
    <div class="link-buttons">
      <RouterLink :to="{ name: 'userPayment' }">
        <button class="link-button">결제내역</button>
      </RouterLink>
    
      <RouterLink :to="{ name: 'userUse' }">
        <button class="link-button">사용내역</button>
      </RouterLink>
    </div>
    <div class="time-info">
      잔여 시간 : {{ formattedRemainTime }}
    </div>
    <div class="button-container">
      <button 
        :class="{ 'start-button-active': isStartButtonActive }" 
        @click="toggleStartButton">
        스타트 버튼
      </button>
      <button 
        :class="{ 'end-button-active': isEndButtonActive }" 
        @click="toggleEndButton" 
        :disabled="!isStartButtonActive">
        엔드 버튼
      </button>
    </div>
    <div class="time-info" v-if="endType === '1'">
      헬롱과 {{ formattedElapsedTime }}동안 함께했어요~ ^^
    </div>
    <div class="time-info" v-if="endType === '0'">
      헬롱과 함께 시작해보아요~!
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch } from 'vue';
import { useUserStore } from '@/stores/user';

const store = useUserStore();

const user_remainTime = ref('');
const name = ref('');
const isStartButtonActive = ref(false);
const isEndButtonActive = ref(false);

onMounted(() => {
  name.value = sessionStorage.getItem('name');
  user_remainTime.value = sessionStorage.getItem('remainTime');
  endType.value = sessionStorage.getItem('endType');

  if (endType.value === '1') {
    isStartButtonActive.value = true;
    isEndButtonActive.value = false;
  } else {
    isStartButtonActive.value = false;
    isEndButtonActive.value = true;
  }

  setInterval(() => {
    currentTime.value = new Date();
  }, 30000);
});

const startType = ref(new Date(sessionStorage.getItem("startType")));
const endType = ref(sessionStorage.getItem("endType"));
const remainTime = ref(sessionStorage.getItem("remainTime"));
const currentTime = ref(new Date());

watch([startType, endType, remainTime], () => {
  sessionStorage.setItem("startType", startType.value);
  sessionStorage.setItem("endType", endType.value);
  sessionStorage.setItem("remainTime", remainTime.value);
});

const formattedElapsedTime = computed(() => {
  const elapsedMilliseconds = currentTime.value - startType.value;
  const elapsedHours = Math.floor(elapsedMilliseconds / (1000 * 60 * 60));
  const elapsedMinutes = Math.floor((elapsedMilliseconds % (1000 * 60 * 60)) / (1000 * 60));
  return `${elapsedHours < 10 ? '0' : ''}${elapsedHours}시간 ${elapsedMinutes < 10 ? '0' : ''}${elapsedMinutes}분`;
});

const formattedRemainTime = computed(() => {
  const totalMinutes = parseInt(user_remainTime.value, 10);
  const hours = Math.floor(totalMinutes / 60);
  const minutes = totalMinutes % 60;
  return `${hours}시간 ${minutes}분`;
});

const toggleStartButton = () => {
  if (parseInt(remainTime.value, 10) >= 60) {
    if (!isStartButtonActive.value) {
      isStartButtonActive.value = true;
      store.startTime();
      isEndButtonActive.value = false;
      endType.value = '1';
      startType.value = new Date();
      currentTime.value = new Date();
    }
  } else {
    alert("시간을 충전해주세요!");
  }
};

const toggleEndButton = () => {
  if (isStartButtonActive.value && !isEndButtonActive.value) {
    isEndButtonActive.value = true;
    isStartButtonActive.value = false;
    store.endTime();
    endType.value = '0';
  }
};
</script>

<style scoped>
.container {
  text-align: center;
  padding: 20px;
}

.title {
  color: #3498db; /* 하늘색 */
  margin-bottom: 20px;
}

.link-buttons {
  margin-bottom: 20px;
}

.link-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #3498db; /* 하늘색 */
  color: white;
  cursor: pointer;
  margin: 0 10px;
  transition: background-color 0.3s;
}

.link-button:hover {
  background-color: #2980b9; /* 어두운 하늘색 */
}

.time-info {
  margin: 10px 0;
  color: #3498db; /* 하늘색 */
}

.button-container {
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.start-button-active {
  background-color: #3498db; /* 하늘색 */
  color: white;
}

.end-button-active {
  background-color: #e74c3c; /* 빨간색 */
  color: white;
}
</style>
