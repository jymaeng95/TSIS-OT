<template>
  <div id='app'>
    <div>
        <h1 class="welcome-msg">Tsis Theater</h1>
        <a href="#" class="btn btn-primary btn-confirm" @click="showModal = true">예매 확인</a> 
    </div>
    <InputPhone v-if="showModal" @close="showModal = false">
          <!--
            you can use custom content here to overwrite
            default content
          -->
          <h3 slot="header">핸드폰 번호 입력</h3>
    </InputPhone>
    <div class="row movie-main">
      <div class="card" style="width: 18rem;">
        <img src="@/assets/clementain.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title"></h5>
          <!-- <h5 class="card-title">{{movieInfo[0].title}}</h5> -->
          <!-- <p class="card-text">상영관 : {{movieInfo[0].room}}</p> -->
          <p class="card-text">상영관 </p>
          <a href="#" class="btn btn-primary btn-reserve">예매 하기</a>
        </div>
      </div>
      <div class="card" style="width: 18rem;">
        <img src="@/assets/hero.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <!-- <h5 class="card-title">{{movieInfo[1].title}}</h5>
          <p class="card-text">상영관 : {{movieInfo[1].room}}</p> -->
          <h5 class="card-title"></h5>
          <p class="card-text">상영관 </p>
          <a href="#" class="btn btn-primary btn-reserve">예매 하기</a>
        </div>
      </div>
      <div class="card" style="width: 18rem;">
        <img src="@/assets/match.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title"></h5>
          <p class="card-text">상영관 </p>
          <!-- <h5 class="card-title">{{movieInfo[2].title}}</h5>
          <p class="card-text">상영관 : {{movieInfo[2].room}}</p> -->
          <a href="#" class="btn btn-primary btn-reserve">예매 하기</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
// import axios from 'axios'
import InputPhone from './components/InputPhone.vue'
export default {
  name: 'main',
  components:{
    InputPhone 
      
    
  },
  data () {
    return {
      movieInfo :[],
      showModal : false
    }
  },
  created : function() {
    // const baseURI = 'http://localhost:8080/movie';
    // axios.get(`${baseURI}`)
    // .then((result) => {
    //   console.log(result)
    //   this.movieInfo = result.data
    // })
  },
  methods: {
    
  }
}
</script>
<style>
  * {
    font-family: 'Cafe24Oneprettynight';
  
  }
  *.btn-primary {
    background: black;
    border: black;
  }
  body {
    background:#ece6cc;  
  }
  @font-face {
    font-family: 'Cafe24Oneprettynight';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Oneprettynight.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  #app {
    width : 100%;
  }
  .welcome-msg {
    font-weight: bold;
    margin-top :10px;
    text-align: center;
   
  }
  .movie-main {
    text-align: center;
    margin-left: 10%;
    margin-right:10%;
  }
  .card {
    margin:50px;
    box-shadow: 3px 3px 3px gray;
  }
  .btn-confirm {
    float:right;
    margin-right: 50px;
  }
  .card-img-top{
    margin-top: 5%;
  }
  
</style>
