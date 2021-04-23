<template>
  <div class="box-office d-flex">
    <div class="left-info">
        <div class="movie-img">
            <div class="card" style="width: 18rem; margin-top:20px;">
                <img src="@/assets/clementain.jpg" class="card-img-top" id ="movie-img" alt="...">
                <div class="card-body">
                    <h5 class="card-title">{{this.data.title}}</h5>
                    <p class="card-text">상영관 : {{this.data.room}}</p>      
                </div>
            </div>
        </div>
        <div class="movie-start-time">
            <select>
                <!-- onChange 따라 value 가지고 event로 전달 -->
                <option v-for="(item,index) in movieInfo" :key="index" :value="item.movieNo" @change="getSeat(item.movieNo)">{{item.startTime}}</option>
            </select>
        </div>
        <div class="movie-watch-people">
            <input type="text" class="input-count" placeholder="3"/>
        </div>
    </div>
    <div class="movie-info" >
        <!-- {{movieInfo[0].startTime}} -->
        <!-- 행*열이 seatCol의 배수일때 마다 알파벳 아스키코드 + 1 -->
        <div class="screen">
            <h2>screen</h2>
        </div>
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
            movieInfo : [],
        
        }
    },
    props: {
        data : {
            type :Object
        }
        
    },
    mounted() {
        const baseURI = 'http://localhost:8080/movie/room';
        axios.get(`${baseURI}/${this.data.room}`)
        .then((result) => { 
            console.log(result)
            this.movieInfo = result.data  
            //seatAxios 
            this.getSeat(this.movieInfo[0].movieNo) 
        })

        switch(this.data.room){
            case 1 : document.getElementById('movie-img').src=require('@/assets/clementain.jpg')
                    break
            case 2 : document.getElementById('movie-img').src=require("@/assets/hero.jpg") 
                    break;
            default : document.getElementById('movie-img').src=require("@/assets/match.jpg")
      
        }
        console.log(this.data)
        
        //초기 화면 좌석 
        //axios를 통해 this.movieInfo[0]의 movieNo 가져오기
        //if 문에서 예약 좌석continue문으로 넘어가고 disabled 추가
        //버튼 클릭 시 버튼 색상 변하게 해놓고, 다시 누르면 다시 없애기 
        //버튼 색상이 변한 개수를 통해서 인원 구하기 
        //인원 * 가격    
    },
    methods : {
        getSeat(movieNo) {
            const baseURI = 'http://localhost:8080/ticketing/seatStatus';
            axios.get(`${baseURI}/${movieNo}`)
            .then((result) => { 
                console.log(result)
                this.paintSeat(result)
            })
            // axios를 통해서 예약된 좌석 다 가져오기 
            // 이중 for문을 돌리면서
        },
        paintSeat(seatInfo) {
            var ascii = 64; 
            var reserveSeat = seatInfo.data.length;     //현재 예약된 좌석 수
            // console.log(seatInfo.data.length)
            var reserveIndex = 0;
            const seatDIV = document.querySelector(".seat-info");

            var div ='';
            for(var i=1;i<=this.data.seatRow;i++) {
                var row = String.fromCharCode(ascii+i);
                div = document.createElement("div");
                div.classList = 'seat-row';
                seatDIV.append(div);
                for(var j=1;j<this.data.seatCol;j++){
                    var btnValue = row+'-'+j
                    console.log(typeof(btnValue))
                    const seatBtn = document.createElement('button');
                    seatBtn.type = 'button';
                    seatBtn.value = btnValue;
                    seatBtn.classList = 'btn btn-seat';
                    seatBtn.innerText = btnValue;   
                    seatBtn.style ="margin:10px; border : 1px solid gray;"

                    if(reserveSeat > reserveIndex && seatInfo.data[reserveIndex] === btnValue) {
                        seatBtn.disabled = true;
                        seatBtn.innerText="X"
                        reserveIndex++;
                    }
                    
                    div.append(seatBtn);
                    
                }
            }
        },        
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

    .movie-start-time, .movie-watch-people {
        margin-left : 50px;
    }
    .movie-start-time select {
        width : 85%;
    }
    .movie-watch-people input {
        width : 85%;
    }
    .left-info {
        border-right : 0.3px dashed gray;
    }
    .movie-info {
        margin-left: 100px;
    }
    .seat-info {
        border : solid 1px gray;
    }
    .screen {
        border : solid 1px gray;
    }
    .screen h2 {
        text-align: center;
    }
    .ticket-info {
        margin-top : 10px;
    }
</style>