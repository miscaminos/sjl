<template>
 <div>
 
   <Portfolio />
   <!-- 머릿말 표시 -->
   <div class="s-portfolio-header">{{ header }}</div>
   <!-- 메세지 표시 -->
   <div class="s-portfolio-message">{{ message }}</div>
   <!-- 포트폴리요 내용 표시 -->
   <div class="s-portfolio-body">
     <!-- v-for를 이용해 포트폴리오 데이터 수만큼 표시 -->
     <div v-for="item in items" :key="item.title">
       <!-- bootstrap의 경고UI를 이용해 표시 -->
       <!-- https://getbootstrap.com/docs/4.1/components/alerts/ -->
       <div class="alert alert-success" role="alert">
         <div class="media">
           <img class="mr-4" v-bind:src="item.image" />
           <div class="media-body">
             <h5 class="mt-0">{{ item.title }}</h5>
             {{ item.description }}
           </div>
         </div>
       </div>
     </div>
   </div>
   
 </div>
</template>

<script>
import request from "request"

export default {
	name: 'Portfolio',
	mounted(){
		request('http://localhost:8081/api/portfolio', function(error, response, body){
		window.console.log('error:', error); // Print the error if one occurred
		window.console.log('statusCode:', response && response.statusCode); // Print the response
		window.console.log('body:',body); //Print the HTML for the Google homepage
		});
	},
	data() {
		return {
			header: "포트폴리오",
			message: "포트폴리오입니다.",
			items: [
			{
				title: "포트폴리오1",
				description: "포트폴리오 설명1",
				image: require("@/assets/logo.png"),
			},
			{
				title: "포트폴리오2",
				description: "포트폴리오 설명2",
				image: require("@/assets/logo.png"),
			},
			{
				title: "포트폴리오3",
				description: "포트폴리오 설명3",
				image: require("@/assets/logo.png"),
			},
			{
				title: "포트폴리오4",
				description: "포트폴리오 설명4",
				image: require("@/assets/logo.png"),
			},
			{
				title: "포트폴리오5",
				description: "포트폴리오 설명5",
				image: require("@/assets/logo.png"),
			},
			],
		};
	},

};
</script>

<script>
//import Portfolio from './components/Portfolio.vue'
//import request from "request"

// export default {
//   name: 'App',
//   components: {
//     Portfolio,
//   },
//   mounted(){
//     request('http://localhost:8081/api/portfolio', function(error, response, body){
//       window.console.log('error:', error); // Print the error if one occurred
//       window.console.log('statusCode:', response && response.statusCode); // Print the response
//       window.console.log('body:',body); //Print the HTML for the Google homepage

//     });
//   }
// }
</script>

<style>
.s-portfolio-header {
 position: relative;
 padding: 0.25em 0;
 font-size: 50px;
 text-align: center;
}
.s-portfolio-header:after {
 content: "";
 display: block;
 height: 4px;
 background: -webkit-linear-gradient(to right, rgb(230, 90, 90), transparent);
 background: linear-gradient(to right, rgb(230, 90, 90), transparent);
}
.s-portfolio-message {
 font-size: 15px;
 text-align: center;
}
.s-portfolio-body {
 margin-left: 10px;
 margin-right: 10px;
 margin-top: 10px;
}
</style>