<template>
  <div>
    <div id="map"></div>
    <div class="buttons">
      <button @click="initMap">내 위치</button>
      <button @click="displayNearestMarker" disabled>가장 가까운 헬스장</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useKakaomapStore } from '@/stores/kakaomap';

let map = null;
const store = useKakaomapStore();
const currentPosition = ref(null);

const initMap = function () {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 4,
  };
  map = new kakao.maps.Map(container, options);

  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      currentPosition.value = { lat, lon };
      const myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    }, (error) => {
      console.error("Geolocation error: ", error);
      map.setCenter(new kakao.maps.LatLng(33.450701, 126.570667));
    });
  } else {
    console.error("Geolocation is not supported by this browser.");
    map.setCenter(new kakao.maps.LatLng(33.450701, 126.570667));
  }
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}`;
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    });
    document.head.appendChild(script);
  }
});

const markerData = ref([
  { latitude: 33.450701, longitude: 126.570667, name: "헬스장 1", address: "제주특별자치도 제주시 첨단로 242" },
  { latitude: 33.450936, longitude: 126.569477, name: "헬스장 2", address: "제주특별자치도 제주시 첨단로 240" },
  { latitude: 33.451095, longitude: 126.568198, name: "헬스장 3", address: "제주특별자치도 제주시 첨단로 238" },
  { latitude: 33.451405, longitude: 126.567036, name: "헬스장 4", address: "제주특별자치도 제주시 첨단로 236" },
  { latitude: 33.451603, longitude: 126.566333, name: "헬스장 5", address: "제주특별자치도 제주시 첨단로 234" },
]);

const displayNearestMarker = function () {
  if (!currentPosition.value) {
    alert("현재 위치를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
    return;
  }

  const currentLatLng = new kakao.maps.LatLng(currentPosition.value.lat, currentPosition.value.lon);
  
  let nearestMarker = null;
  let minDistance = Number.MAX_VALUE;

  markerData.value.forEach(marker => {
    const markerLatLng = new kakao.maps.LatLng(marker.latitude, marker.longitude);
    const distance = kakao.maps.services.Util.getDistance(currentLatLng, markerLatLng);
    
    if (distance < minDistance) {
      minDistance = distance;
      nearestMarker = marker;
    }
  });

  if (nearestMarker) {
    const position = new kakao.maps.LatLng(nearestMarker.latitude, nearestMarker.longitude);
    new kakao.maps.Marker({
      map,
      position: position,
    });

    map.setCenter(position);

    const infowindow = new kakao.maps.InfoWindow({
      content: `<div style="padding:5px;">${nearestMarker.name}<br>${nearestMarker.address}</div>`,
    });

    infowindow.open(map, new kakao.maps.Marker({
      map,
      position: position,
    }));
  }
};

</script>

<style scoped>
#map {
  width: 100%;
  height: 254px;
}
.buttons {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
button {
  margin: 0 10px;
  padding: 10px 20px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #0056b3;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
