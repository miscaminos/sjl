<template>
 <div id="app">
   <!-- 부트스트랩 Navbar UI 표시 -->
   <!-- https://getbootstrap.com/docs/4.1/components/navbar/ -->
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
     <div id="navbarNavAltMarkup">
       <div class="navbar-nav">
         <!-- 페이지 이동 설정 -->
         <router-link class="nav-item nav-link" to="/">Home</router-link>
         <router-link class="nav-item nav-link" to="/about">About</router-link>
         <router-link class="nav-item nav-link" to="/portfolio">Portfolio</router-link>
         <router-link class="nav-item nav-link" to="/contents">Contents</router-link>
         <router-link class="nav-item nav-link" to="/add">Add New</router-link>
         <router-link class="nav-item nav-link" to="/helloWorld">Hello World</router-link>
       </div>
     </div>
   </nav>
   <transition name="moveInUp">
   <router-view/>
   </transition>
 </div>
</template>

<script>
export default {
 name: "app",
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.moveInUp-enter-active{
  animation: fadeIn 1s ease-in;
}
@keyframes fadeIn{
  0%{
    opacity: 0;
  }
  50%{
    opacity: 0.5;
  }
  100%{
    opacity: 1;
  }
}
</style>
