<template>
  <div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import axios from 'axios';
import { defineProps } from 'vue';

const props = defineProps({
  totalHours: {
    type: Number,
    required: true
  },
  totalPrice: {
    type: Number,
    required: true
  }
});

const onPayment = () => {

    // 세션 스토리지에서 사용자 정보 가져오기
  const buyerName = sessionStorage.getItem('name');
  const buyerTel = sessionStorage.getItem('phNum');
  const buyerEmail = sessionStorage.getItem('email');
  /* 1. 가맹점 식별하기 */
  const { IMP } = window;
  IMP.init('imp17814543');
  
  /* 2. 결제 데이터 정의하기 */
  const data = {
    pg: 'html5_inicis',                           
    pay_method: 'card',                           
    merchant_uid: `mid_${new Date().getTime()}`,   
    amount: props.totalPrice,                     
    name: props.totalHours,                  
    buyer_name: buyerName,                           
    buyer_tel: buyerTel,                     
    buyer_email: buyerEmail,            
  };
  
  /* 4. 결제 창 호출하기 */
  IMP.request_pay(data, callback);
};

const callback = (response) => {
  /* 3. 콜백 함수 정의하기 */
  const {
    success,
    merchant_uid,
    error_msg,
    data, 
  } = response;
  
  console.log(response);
  
  if (success) {
    axios({
      url: 'http://localhost:8080/pay',
      method: 'POST',
      data: response
    })
    .then((response) => {
      console.log(".data : " +response.data)
      alert('결제 성공');
      const remainTime = parseInt(sessionStorage.getItem('remainTime'));
      const responseData = parseInt(response.data); // 응답 데이터를 정수로 변환
      sessionStorage.setItem('remainTime', responseData + remainTime)
      window.location.href = '/main';
    })
  } else {
    alert(`결제 실패: ${error_msg}`);
  }
};

onMounted(() => {
  // jQuery 로드
  const jqueryScript = document.createElement('script');
  jqueryScript.src = 'https://code.jquery.com/jquery-1.12.4.min.js';
  document.head.appendChild(jqueryScript);

  // iamport.payment.js 로드
  const iamportScript = document.createElement('script');
  iamportScript.src = 'https://cdn.iamport.kr/js/iamport.payment-1.2.0.js';
  document.head.appendChild(iamportScript);

  // iamport.payment.js 로드 후에 onPayment 메소드 실행
  iamportScript.onload = () => {
    onPayment();
  };
});
</script>

<style scoped>
</style>
