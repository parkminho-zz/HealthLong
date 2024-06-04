<template>
  <div class="container mt-5">
    <div class="card p-5 shadow-lg text-center">
      <h2 class="card-title mb-4">사용자 등록</h2>
      <div class="form-group mb-3">
        <input type="text" class="form-control form-control-lg mx-auto" placeholder="이름" name="name" v-model="user.name">
      </div>
      <div class="form-group mb-3">
        <input type="text" class="form-control form-control-lg mx-auto" name="email" v-model="email" readonly>
      </div>
      <div class="form-group mb-4">
        <input type="text" class="form-control form-control-lg mx-auto" placeholder="핸드폰번호" name="phNum" v-model="user.phNum">
      </div>
      <div class="text-center">
        <button class="btn btn-primary btn-lg w-50" @click="registUser">등록</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'
import { useUserStore } from '@/stores/user';

const router = useRouter()
const route = useRoute()

const store = useUserStore()

const registUser = function () {
  user.value.email = email.value;
  store.registUser(user.value)
};

const email = ref('');

const getToken = async(code) => {
    console.log(code)
    axios.get("http://localhost:8080/kakaologin?code=" + code )
    .then((res)=>{
      console.log(res.data.code)

      //맵객체 != 코드값 이니까 로그인된거임
      if(res.data.code === undefined){
        console.log(res.data)
        sessionStorage.setItem('access-token',res.data["access-token"])
        sessionStorage.setItem('email',res.data["email"])
        sessionStorage.setItem('name',res.data["name"])
        sessionStorage.setItem('phNum',res.data["phNum"])
        sessionStorage.setItem('type',res.data["type"])
        sessionStorage.setItem('endType',res.data["isEndTime"])
        sessionStorage.setItem('remainTime', res.data["isRemainTime"])
        sessionStorage.setItem('startType', res.data["isStartTime"])
        router.push({ name: 'mainView' });
      }
      email.value = res.data.getUserInfo

    })
  }

  onMounted(() => {
    // 마운트 이후에 실행할 코드 작성
    const code = String(route.query.code)
    console.log(code);
    getToken(code);
  });
  const user = ref({
    name: "",
    password:"",
    email: "",
    phNum: "",
})
const handleBeforeUnload = (event) => {

    event.preventDefault();
    event.returnValue = ''; // 표준에 따라 추가

    const userConfirmed = window.confirm();
    if (userConfirmed) {
      window.location.href = '/';
    } else {
      event.returnValue = false;
    }
}

window.addEventListener('beforeunload', handleBeforeUnload);

onBeforeUnmount(() => {
  window.removeEventListener('beforeunload', handleBeforeUnload);
});
</script>

<style scoped>
.container {
  padding-top: 10%;
  max-width: 600px;
  margin: auto;
}

.card {
  border: none;
  border-radius: 15px;
  background-color: #f8f9fa;
  padding: 40px; /* 추가된 padding */
}

.card-title {
  font-weight: bold;
  color: #007bff;
}

.form-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px; /* 여백 추가 */
}

.form-control {
  height: 45px;
  font-size: 1rem;
  border-radius: 10px;
  border: 1px solid #ced4da;
  max-width: 80%;
  margin-bottom: 10px; /* 입력 필드 사이의 여백 추가 */
}

.form-control:focus {
  box-shadow: none;
  border-color: #80bdff;
}

.btn {
  height: 45px;
  font-size: 1.2rem;
  font-weight: bold;
  border-radius: 15px;
  max-width: 80%; /* 버튼 너비 조정 */
  margin-top: 20px; /* 버튼 위의 여백 추가 */
}

.btn-primary {
  background-color: skyblue;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

.text-center {
  text-align: center;
}

</style>
  