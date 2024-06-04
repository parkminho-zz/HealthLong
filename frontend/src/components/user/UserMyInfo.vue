<template>
    <div class="my-page">
      <h1>My Page</h1>
        <div>
          <label for="name">Name:</label>
          <input type="text" v-model="name" id="name" />
        </div>
        <div>
          <label >ID:</label>
          <input type="text" v-model="email" id="email" disabled />
        </div>
        <div>
          <label for="phNum">Phone Number:</label>
          <input type="text" v-model="phNum" id="phone" disabled/>
        </div>
        <div v-if="type != 2">
          <label for="password">Password:</label>
          <input type="password" id="password" value = "password is secret" disabled/>
          <label for="password">Password:</label>
          <input type="password"  v-model="password" id="password" placeholder ="새 비밀번호" />
        </div>
        <button type="submit" @click = "updateUser">Update</button>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useUserStore } from "@/stores/user";
  import { useRouter } from 'vue-router';
  
  const type = sessionStorage.getItem("type")
  
const name = ref(sessionStorage.getItem('name') || '');
const email = ref(sessionStorage.getItem('email') || '');
const phNum = ref(sessionStorage.getItem('phNum') || '');
const password = ref('');  // 새로운 비밀번호 필드 추가

  const store = useUserStore();
  
  const updateUser = () => {
  if (type == 1) {
    const updatedUser = {
      email: email.value,
      name: name.value,
      phNum: phNum.value,
      type: type,
      password: password.value  // 비밀번호를 업데이트 시에 포함
    };
    store.updateUser(updatedUser);
  } else if (type == 2) {
    const updatedKakaoUser = {
      email: email.value,
      name: name.value,
      type: type,
    };
    store.updateKakaoUser(updatedKakaoUser);
  }
};
</script>
  
  <style scoped>
  .my-page {
    max-width: 600px;
    margin: auto;
    padding: 1rem;
  }
  
  .my-page form div {
    margin-bottom: 1rem;
  }
  
  .my-page label {
    display: block;
    margin-bottom: 0.5rem;
  }
  
  .my-page input {
    width: 100%;
    padding: 0.5rem;
    box-sizing: border-box;
  }
  
  .my-page button {
    padding: 0.5rem 1rem;
  }
  </style>
  