<template>
    <div class="signup-form">
      <h2>회원가입</h2>
      <form @submit.prevent="signup">
        <div class="radio-group">
          <label>
            <input type="radio" v-model="signupCredentials.type" value="personal" required />
            개인 회원가입
          </label>
          <label>
            <input type="radio" v-model="signupCredentials.type" value="company" required />
            기업 회원가입
          </label>
        </div>
        <input type="email" v-model="signupCredentials.email" placeholder="이메일" required />
        <input type="password" v-model="signupCredentials.password" placeholder="비밀번호" required />
        <input type="text" v-model="signupCredentials.name" placeholder="이름" required />
        <input type="text" v-model="signupCredentials.phNum" placeholder="전화번호" required />
        <div v-if="signupCredentials.type === 'company'">
          <input type="text" v-model="signupCredentials.businessNumber" placeholder="사업자 번호" required />
        </div>
        <button type="submit" :disabled="isFormInvalid">회원가입</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, watch } from 'vue';
  import { useRouter } from 'vue-router';
  import axios from 'axios';
  
  const signupCredentials = ref({
    type: '',
    email: '',
    password: '',
    name: '',
    phNum: '',
    businessNumber: '',
  });
  
  const router = useRouter();
  
  const isFormInvalid = computed(() => {
    if (signupCredentials.value.type === 'personal') {
      return !signupCredentials.value.email || !signupCredentials.value.password || !signupCredentials.value.name || !signupCredentials.value.phNum;
    } else if (signupCredentials.value.type === 'company') {
      return !signupCredentials.value.email || !signupCredentials.value.password || !signupCredentials.value.name || !signupCredentials.value.phNum || !signupCredentials.value.businessNumber;
    }
    return true;
  });
  
  watch(() => signupCredentials.value.type, (newType) => {
    signupCredentials.value.email = '';
    signupCredentials.value.password = '';
    signupCredentials.value.name = '';
    signupCredentials.value.phNum = '';
    signupCredentials.value.businessNumber = '';
  });
  
  const signup = () => {
    if (isFormInvalid.value) {
      alert('모든 필드를 입력해주세요.');
      return;
    }
  
    let payload = {};
    let url = '';
  
    if (signupCredentials.value.type === 'personal') {
      payload = {
        email: signupCredentials.value.email,
        password: signupCredentials.value.password,
        name: signupCredentials.value.name,
        phNum: signupCredentials.value.phNum,
        userType: 1,
      };
      url = 'http://localhost:8080/regist1';
    } else {
      payload = {
        userType: 3,
        corpEmail: signupCredentials.value.email,
        corpPassword: signupCredentials.value.password,
        corpName: signupCredentials.value.name,
        corpPhNum: signupCredentials.value.phNum,
        corpNum: signupCredentials.value.businessNumber,
      };
      url = 'http://localhost:8080/regist3';
    }
  
    axios({
      url: url,
      method: 'POST',
      data: payload
    })
    .then((response) => {
        console.log('회원가입 성공:', response.data);
        alert('회원가입에 성공하였습니다. 로그인을 진행해주세요.');
        router.push({ name: 'introduceView' });

    })
    .catch((error) => {
      console.log(payload);
      console.error('회원가입 실패:', error);
      alert('회원가입에 실패했습니다. 다시 시도해주세요.');
    });
  };
  </script>
  
  <style scoped>
  .signup-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    text-align: center;
  }
  
  .signup-form h2 {
    margin-bottom: 20px;
  }
  
  .signup-form input {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .signup-form button {
    width: 100%;
    padding: 10px;
    background-color: skyblue;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .signup-form button:disabled {
    background-color: lightgray;
    cursor: not-allowed;
  }
  
  .radio-group {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  
  .radio-group label {
    margin: 0 10px;
    cursor: pointer;
  }
  </style>
  