<template>
  <div class="box-office d-flex">
    <div class="left-info">
        <div class="movie-img">
            <div class="card" style="width: 18rem;">
                <img src="@/assets/clementain.jpg" class="card-img-top" id ="movie-img" alt="...">
                <div class="card-body">
                    <h5 class="card-title">{{this.data.title}}</h5>
                    <p class="card-text">상영관 : {{this.data.room}}</p>      
                </div>
            </div>
        </div>
        <div class="movie-start-time">
            <select>
                <option v-for="(item,index) in movieInfo" :key="index" :value="item.movieNo">{{item.startTime}}</option>
            </select>
        </div>
        <div class="movie-watch-people">
            <input type="text" class="input-count" placeholder="3"/>
        </div>
    </div>
    <div class="movie-info">
        <!-- {{movieInfo[0].startTime}} -->
        <!-- 행*열이 seatCol의 배수일때 마다 알파벳 아스키코드 + 1 -->
        <div class="seat-info">
            
        </div>

        <div class="ticket-info d-flex">
            <p class="p-price">가격 : </p>
            <p class="p-price">가격 * 인원수</p>
            <button class="ticketing btn btn-primary">예매</button>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name : "BoxOffice",
    data() {
        return {
            movieInfo : []
        }
    },
    props: {
        data : {
            type :Object
        }
        
    },
    created : function() {
        const baseURI = 'http://localhost:8080/movie/room';
        axios.get(`${baseURI}/${this.data.room}`)
        .then((result) => { 
        console.log(result)
        this.movieInfo = result.data
       })
    },
    mounted() {
        switch(this.data.room){
            case 1 : document.getElementById('movie-img').src=require('@/assets/clementain.jpg')
                    break
            case 2 : document.getElementById('movie-img').src=require("@/assets/hero.jpg") 
                    break;
            default : document.getElementById('movie-img').src=require("@/assets/match.jpg")
      
        }
        console.log(this.data)
     
    }
    
}

</script>

<style>
    .box-office {
        margin :2%;
    }
    .input {
        border-radius: 2px solid;
    }
</style>