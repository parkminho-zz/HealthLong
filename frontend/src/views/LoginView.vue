<template>
    <div class="page-container">
      <div class="login-tabs">
        <button :class="{ active: activeTab === 'personal' }" @click="activeTab = 'personal'">개인</button>
        <button :class="{ active: activeTab === 'company' }" @click="activeTab = 'company'">기업</button>
      </div>
      <div v-if="activeTab === 'personal'" class="login-form">
        <h2>개인 로그인</h2>
          <input type="text" v-model="personalCredentials.username" placeholder="아이디" required />
          <input type="password" v-model="personalCredentials.password" placeholder="비밀번호" required />
          <button type="submit" @click = "loginUser" >로그인</button>
      </div>
      <div v-if="activeTab === 'company'" class="login-form">
        <h2>기업 로그인</h2>
          <input type="text" v-model="companyCredentials.username" placeholder="아이디" required />
          <input type="password" v-model="companyCredentials.password" placeholder="비밀번호" required />
          <button type="submit">로그인</button>
    </div>
        <div class="btn-area">
      <a class="kakao-login-btn" @click="kakaoLogin">
        <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
      </a>
    </div>
    <br><router-link class="signup-link" :to="{ name: 'userCreate' }">회원가입</router-link>
    <p id="token-result"></p>
  </div>
 </template>
  
  <script setup>
  import { onMounted, ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { useUserStore } from "@/stores/user";


  let isSession = false

  onMounted(() => {
    if (sessionStorage.getItem("access-token")) {
    // 세션에 access-token이 존재하는 경우
    isSession = true;
    window.location.href = '/main';
  } else {
    // 세션에 access-token이 존재하지 않는 경우
    isSession = false;
  }
  
  })

  const activeTab = ref('personal');
  const personalCredentials = ref({ username: '', password: '', userType: 1 });
  const companyCredentials = ref({ username: '', password: '', userType: 3 });
  const router = useRouter();
  
  const store = useUserStore()

  const loginUser = () =>{
    store.loginUser(personalCredentials)
  }

  const kakaoLogin = function() {
      const redirect_uri = 'http://localhost:5173/kakaologin';
      const clientId = 'b6f3094bb65cd61e7c5194da1aceada7'; //REST API 키
      const Auth_url = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirect_uri}`;
      window.location.href = Auth_url;
    }
  </script>
  
  <style scoped>
  .page-container {
    position: relative;
    padding-top: 90px;
    text-align: center;
  }
  
  .login-tabs {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  
  .login-tabs button {
    padding: 10px 20px;
    border: none;
    background-color: lightgray;
    cursor: pointer;
    margin: 0 5px;
    border-radius: 5px;
  }
  
  .login-tabs button.active {
    background-color: skyblue;
    color: white;
  }
  
  .login-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
  }
  
  .login-form h2 {
    margin-bottom: 20px;
  }
  
  .login-form input {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .login-form button {
    width: 100%;
    padding: 10px;
    background-color: skyblue;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .btn-area {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  
  .btn-area a, .btn-area .signup-link {
    margin: 0 10px;
  }
  
  .signup-link {
    display: block;
    text-align: center;
    color: blue;
    cursor: pointer;
  }
  </style>