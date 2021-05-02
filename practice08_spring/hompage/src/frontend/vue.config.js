/**
 * DeockStory 블로그 참고부분:
 * [omitted]1.local에서 개발할때에도 하나의 서버만 있는것처럼 개발할 수 있도록 설정하기: 
 * module.exports> devServer:
 * SPA(Single Page Application)의 frontend부분을 담당하는 
 * Vue.js dev server(localhost:8081/api)로 오는 요청을 받으면
 * 여기 proxy설정의 target으로 입력한 
 * spring server(localhost:8800/api)를 향해
 * 해당 요청을 다시 쏴준다. 
 * 그리고 응답을 받으면 Vue.js dev server로 받은 응답을 되돌려준다. 
 * 
 * [applied]2.vue파일들의 빌드 결과물을 바로 main/resources/static으로 이동시키기:
 * module.exports> outputDir:
 * 아무런 설정없이 빌드하면 따로 dist라는 디렉토리안에 빌드 결과물이 저장되기때문에
 * 매번 static폴더로 이동시켜야한다. 
 * 아얘 vue 빌드 결과물이 저장되는 디렉토리를 변경한다.
 * 
 * Bezkoder 참고부분:
 * [applied]1.axios사용 시, 단순하게 vue dev server port번호만
 * devServer에 입력해준다. (axios를 활용하는 api제공 port 지정은
 * http-common.js에 따로 입력한다.)  
 * 
 */
const path = require('path')
module.exports = {
	outputDir: path.resolve(__dirname, "./"+"main/resources/static"),
	devServer: {
		port: 8081
		// proxy: {
		// 	'/api':{
		// 		target: 'http://localhost:8800', //spring server localhost port number:8800
		// 		ws: true,
		// 		changeOrigin: true
		// 	},	
		// }
	}	
}