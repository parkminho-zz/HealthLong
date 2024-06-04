<template>
    <div>
        <h4>boardview</h4>
        <RouterView/>
    </div>
</template>

<script setup>
    import { onMounted} from 'vue';
    
    let isSession = false
    onMounted(() => {
    if (!sessionStorage.getItem("access-token")) {
    isSession = false;
    window.location.href = '/';
  }
})
</script>

<style scoped>

</style>