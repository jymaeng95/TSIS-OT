<template>
    <transition name="modal">
    <div class="modal-mask" @click.self="$emit('close')">
      <div class="modal-wrapper" @click.self="$emit('close')" >
        <div class="modal-container-ticketing" style="width:900px; height:900px;">

          <div class="modal-header">
            <slot name="header">
              <h2>"{{this.seat}}" 님의 예매 내역 {{this.movieInfo.price}} {{this.movieInfo.title}} {{this.movieInfo.room}} {{this.movieInfo.countPeople}} {{this.movieInfo.movieNo}}</h2>             
            </slot>
          </div>

          <div class="modal-body">
            <div class="ticketing-info d-flex">
              <div class="card" style="width: 350px; margin:10px; margin-top:0px;">
                <img src="@/assets/clementain.jpg" class="card-img-top" id ="movie-img" alt="...">
                <div class="card-body">
                  <h4 class="p-title">{{this.movieInfo.title}}</h4>
                </div>
              </div>
              <div class="card" style="width: 350px; margin:10px; margin-top:0px; margin-left :70px;">
                <div class="card-body">
                  <hr>
                    <h3>{{this.movieInfo.title}}</h3>
                  <hr>
                    <h4>상영관 : {{this.movieInfo.room}} 관</h4>
                    <h4>상영 시간  : {{this.movieInfo.startTime}}</h4>
                    <h4>좌석 : {{this.seat}}</h4>
                    <h4>인원 : {{this.movieInfo.countPeople}}</h4>
                    <h4>가격 : {{this.movieInfo.price}}</h4>
                    <img src="@/assets/bacord.png" class="card-img-bacord" id="bacord-img" alt="..." style="width:330px;">
                </div>
              </div>
            </div>
          </div>
    
          <div class="modal-footer">
            <slot name="footer">
     
              <!-- <button class="modal-default-button" @click="$emit('close')"> -->
              <button class="modal-default-button btn btn-primary" @click="$emit('close')">
                확인
              </button> 
            </slot>
          </div>
        </div>
       
      </div>
    </div>
  </transition>
</template>

<script>
export default {
    name : 'ticketing',
    props: {
        seat : {
            type :String
        },
        movieInfo : {
            type : Object
        }
        
    },
    mounted() {
      switch(this.movieInfo.room){
            case 1 : document.getElementById('movie-img').src=require('@/assets/clementain.jpg')
                    break
            case 2 : document.getElementById('movie-img').src=require("@/assets/hero.jpg") 
                    break;
            default : document.getElementById('movie-img').src=require("@/assets/match.jpg")
  
        }  
    },
    methods : {
      
    }
}
</script>

<style>
.modal-container-ticketing {
  width: 900px;
  height : 900px;

  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.p-title {
  text-align: center;
}
.card h4 {
  margin-top : 40px;
}
</style>