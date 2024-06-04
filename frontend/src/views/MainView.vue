<template>
<div class = "fullback">
  <div class="page-container">
    <div class="box">
      <div class="component-container">
        <div v-show="showComponent1" class="component">
          <test/>
        </div>
        <div v-show="showComponent2" class="component">
          <test2/>
        </div>  
      </div>
      <div class="content">
        <div class="content-up">
          <div class="rendering" @mouseover="showComponent1 = true, showComponent2 = false">
            <h4>나의 이용권</h4>
          </div>
        </div>
        <div class="separator"></div>
        <div class="content-down">
          <div class="rendering" @mouseover="showComponent2 = true, showComponent1 = false">
            <h4>지도</h4>
          </div>
        </div>
      </div>   
    </div>
  </div>
  <br>
  <br>
  <section class="banner">
    <div class="banner1">
      <RouterLink :to="{ name: 'paymentView' }">
        <img src="@/assets/payment.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
    <div class="banner2">
      <RouterLink :to="{ name: 'userMyInfo' }">
        <img src="@/assets/mypage.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
    <div class="banner3">
      <RouterLink :to="{ name: 'boardList' }">
        <img src="@/assets/board.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
    <div class="banner4">
      <RouterLink :to="{ name: 'youtube' }">
        <img src="@/assets/youtube.png" alt="Logo" class="logo">
      </RouterLink>
    </div>
  </section>
</div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import test from '@/views/test.vue';
import test2 from '@/views/test2.vue';

export default {
  components: {
    test,
    test2
  },
  setup() {
    const router = useRouter();
    const showComponent1 = ref(true);
    const showComponent2 = ref(false);

    onMounted(() => {
      if (!sessionStorage.getItem("access-token")) {
        router.push('/');
      }
    });

    return {
      showComponent1,
      showComponent2
    };
  }
};
</script>

<style>
.fullback{
  position: relative;
  padding-top:90px;
  text-align: center;
  background-image: url('@/assets/background.png'); 
  background-size: cover;
  background-position: center; 
  background-repeat: no-repeat; 
}

.banner {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 50px;
  width: 67%;
  padding: 40px;
  padding-left: 18.5%;
}
.banner1 img,.banner2 img
,.banner3 img,.banner4 img{
  padding-top: 30px;
  width: 80%;
  height: 15%;
  height: 100px;
  padding: 20px;
  background-color: #f0f0f0;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid #ccc;
  border-color: rgb(35, 144, 186);
  border-radius: 10px; /* 회색 박스 테두리 둥글게 */

}
.page-container {
  position: relative;
  padding-top: 40px;
  text-align: center;
  
}

.box {
  position: relative;
  width: 60%; /* 박스의 너비를 자동으로 설정하여 내용에 맞게 조정 */
  margin: auto;
  background-color: #f0f0f0; /* 회색 박스 배경색 */
  border-radius: 10px; /* 회색 박스 테두리 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
  padding: 20px; /* 내용에 대한 여백 설정 */
  display: flex; /* 내용을 가로로 배열하기 위해 flex 설정 */
  justify-content: space-between; /* 내용을 양쪽 끝에 배치하기 위해 설정 */
  border: 2px solid #2390ba; /* 구분 선을 위쪽에 추가 */
}

.component-container{
  border-right: 2px solid rgb(35, 144, 186); /* 구분 선을 위쪽에 추가 */
  flex: 3;
}

.img.logo{
  border: 2px solid black; /* 구분 선을 위쪽에 추가 */

}

.content {
  flex: 1; /* 가용 공간을 균등하게 나누도록 설정 */
}

.content-up,
.content-down {
  flex: 1; /* 좌우 컨테이너가 동일한 너비를 갖도록 설정 */ /* 그럼 제가 선배맘에 탕 탕 후루후루 탕탕 후루루루루 탕탕 후루후루루 그럼제가 선배맘에 탕탕 후루후루루 탕탕 후루루루루루 탕탕 후루루루루 단짠딴짠 뿜빰빰빰빰밤밤 그럼제가 선배맘에 탕탕 후루루루 탕탕 후루루루루 탕탕 후루루루 그럼제가 선배맘에 탕탕 후루루루 언제까지 어깨춤을 추게할꺼야 탈골되잖아 탈골탈골탈골탈골탈골  그쪽도 자겁치꽈를 아는지~? 웍웍웍 웍웍웍 그쪽도 작업치꽈를 아는지이~? 우우우엑! 우엑! 그쪽도 자겁치꽈를 아는지~~*/
  text-align: center;
}

.rendering {
  height: 30px;
  padding: 50px; /* 내용에 적절한 여백 추가 */
  margin: 0 10px; /* 내용 사이 간격 설정 */
  cursor: pointer; /* 마우스 커서를 포인터로 변경하여 클릭 가능한 상태로 표시 */
  text-align: center;
}

.separator {
  border-top: 2px solid rgb(35, 144, 186); /* 구분 선을 위쪽에 추가 */
  border-left: 2px solid rgb(35, 144, 186); /* 왼쪽에 구분 선 추가 */
  margin: 20px 0; /* 위아래 여백 설정 */
}


.mouse-effect {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  /* 마우스 효과 스타일 */
}
.component-container {
  padding-top: 20px; /* 컴포넌트 영역의 위 여백 설정 */
  display: flex;
  justify-content: flex-end; /* 컴포넌트를 오른쪽으로 정렬 */
}

.component {
  flex: 1; /* 컴포넌트가 동일한 너비를 갖도록 설정 */
}

/* 다른 필요한 스타일들을 추가하세요 */
</style>
