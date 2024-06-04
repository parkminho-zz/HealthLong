import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = `http://localhost:8080/user`

export const useUserStore = defineStore('user', () => {
  
  const startTime = function () {
    const email = sessionStorage.getItem("email");
    const [username, domain] = email.split("@")
    axios({
      url: `${REST_USER_API}/starttime`,
      method: 'POST',
      data: {
        username: username,
        domain: domain
      }
    })
    .then((response) => {
      sessionStorage.setItem("endType", 1)
      sessionStorage.setItem("startType",response.data)
    })
  }

  const endTime = function () {
    const email = sessionStorage.getItem("email");
    const [username, domain] = email.split("@")
    axios({
      url: `${REST_USER_API}/endtime`,
      method: 'POST',
      data: {
        username: username,
        domain: domain,
        ticketStart: sessionStorage.getItem("startType"),
        endTime: 0,
      }
    })
    .then((response) => {
      console.log(response)
      sessionStorage.setItem("remainTime", response.data)      
      sessionStorage.setItem("endType", 0)
      alert("운동을 완료했습니다!")
      window.location.href = '/main'
    })
  }

  const registUser = function (user) {
    console.log(user)
    axios({
      url: `${REST_USER_API}/regist`,
      method: 'POST',
      // 아래 작업하지 않아도 그냥 JSON 형태로 Content-type을 결정해서 보내버림
      // headers: {
      //   "Content-Type": "applcation/json"
      // },
      data: user
    })
      .then(() => {
        alert("회원가입되었습니다. 다시 로그인 해주세요.");
        router.push({name : 'introduceView'})
      })
      .catch((err) => {
        console.log(err);
        alert("일반가입 회원으로 이미 사용중인 이메일입니다. 일반 로그인을 이용해주세요.");
        router.push({ name: 'introduceView' });
    })
  }

  let paymentList = ref([])
  const getPaymentList = function () {
    const email = sessionStorage.getItem("email");
    const [username, domain] = email.split("@")
    axios({
      url: `${REST_USER_API}/payment`,
      method: 'POST',
      // 아래 작업하지 않아도 그냥 JSON 형태로 Content-type을 결정해서 보내버림
      // headers: {
      //   "Content-Type": "applcation/json"
      // },
      data: {
        username: username,
        domain: domain
      }
    })
    .then((response) => {
      paymentList.value = response.data
      
    })
  }

  let useHistoryList = ref([])
  const getUseList = function () {
    const email = sessionStorage.getItem("email");
    const [username, domain] = email.split("@")
    axios({
      url: `${REST_USER_API}/usegym`,
      method: 'POST',
      // 아래 작업하지 않아도 그냥 JSON 형태로 Content-type을 결정해서 보내버림
      // headers: {
      //   "Content-Type": "applcation/json"
      // },
      data: {
        username: username,
        domain: domain
      }
    })
    .then((response) => {
      useHistoryList.value = response.data
    })
  }

  const updateUser = function (updatedUser) {
    axios({
      url: `${REST_USER_API}`,
      method: 'POST',
      data: updatedUser
    })
    .then((response) => {
      let name = response.data.name
      sessionStorage.setItem("name", name )
      alert("정보수정이 완료되었습니다.")
      window.location.href = '/main'
    })
  }

  const updateKakaoUser = function (updatedKakaoUser) {
    axios({
      url: `${REST_USER_API}`,
      method: 'POST',
      data: updatedKakaoUser
      })
    .then((response) => {
      let name = response.data.name
      sessionStorage.setItem("name", name )
      alert("정보수정이 완료되었습니다.")
      window.location.href = '/main'
    })
  }

  const loginUser = function (user) {
    axios({
      url: `${REST_USER_API}/login`,
      method: 'POST',
      data : user.value
      })
    .then((res) => {
      console.log(res.data)
      sessionStorage.setItem('access-token',res.data["access-token"])
      sessionStorage.setItem('email',res.data["email"])
      sessionStorage.setItem('name',res.data["name"])
      sessionStorage.setItem('phNum',res.data["phNum"])
      sessionStorage.setItem('type',res.data["type"])
      sessionStorage.setItem('endType',res.data["isEndTime"])
      sessionStorage.setItem('remainTime', res.data["isRemainTime"])
      sessionStorage.setItem('startType', res.data["isStartTime"])

      window.location.href = '/main';
    })
    .catch((error) => {
      if (error.response.status === 401) {
        // 로그인 실패 시
        alert("로그인에 실패했습니다.");
        // 아이디, 비밀번호 필드 초기화
        user.value.username = '';
        user.value.password = '';
      } else {
        // 기타 오류 처리
        console.error("로그인 오류:", error);
        alert("로그인 중 오류가 발생했습니다.");
      }
    });
  }

  return { registUser, paymentList, getPaymentList, startTime, endTime, getUseList, useHistoryList, updateUser , updateKakaoUser, loginUser }
})
