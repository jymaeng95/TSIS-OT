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
            <button type="button" class="btn btn-start-time" v-for="(item,index) in movieInfo" 
                :key="index" :value="item.movieNo" @click="getSeat(item.movieNo,item.price)" >{{item.startTime}}</button>
        </div>
        <!-- 남은 좌석 / 전체 좌석 -->
        <div class="current-seat-status">
            <p class="cur-seat"></p>
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
            <p class="p-people">인원 : </p>
            <p class="p-people-count">0 명</p>
            <p class="p-price" style="margin-left : 70px;">가격 : </p>
            <p class="p-price-won">0 원</p>
            <p class="p-seat" style="margin-left : 50px;">좌석 : </p>
            <p class="p-seat-info"></p>
            <input type="hidden" class="hidden-seat-info"/>
            <button class="ticketing btn btn-primary" style="" @click="goTicketing($event)">예매</button>
        </div>
    </div>
    <Ticketing v-if="ticketing" @close="ticketing = false" :movieInfo ='sentMovieInfo' :seat='selectedSeat'/>
  </div>
</template>

<script>
import axios from 'axios'
import Ticketing from '@/components/Ticketing.vue'
export default {
    name : "BoxOffice",
    components : {
        Ticketing
    },
    data() {
        return {
            movieInfo : [],
            selectedSeat : [],
            sentMovieInfo : {},
            countSeat : 0,
            ticketing : false,
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
            console.log(this.movieInfo)
            console.log("---")
            //seatAxios 
            this.getSeat(this.movieInfo[0].movieNo,this.movieInfo[0].price) 
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
        getSeat(movieNo,price) {
            this.seatInfo = new Array();
            const ticketingBtn = document.querySelector('.ticketing');
            ticketingBtn.value = movieNo;
            
            const baseURI = 'http://localhost:8080/ticketing/seatStatus/movieNo';
            axios.get(`${baseURI}/${movieNo}`)
            .then((result) => { 
                console.log(result)
                this.paintSeat(result,price)
                const curSeat = document.querySelector('.cur-seat')
                curSeat.innerText = '상영관 좌석 상태 : '+(this.data.totalSeat - result.data.length)+ ' / '+this.data.totalSeat;
            })
            // axios를 통해서 예약된 좌석 다 가져오기 
            // 이중 for문을 돌리면서
        },
        paintSeat(seatInfo,moviePrice) {
            
            var ascii = 64; 
            var reserveSeat = seatInfo.data.length;     //현재 예약된 좌석 수
            // console.log(seatInfo.data.length)

            
            var reserveIndex = 0;
            const seatDIV = document.querySelector(".seat-info");
            while(seatDIV.hasChildNodes())
                seatDIV.removeChild(seatDIV.firstChild);
            let clickSeat = new Array();
            var div ='';
            let count = 0;
            for(var i=1;i<=this.data.seatRow;i++) {
                var row = String.fromCharCode(ascii+i);
                div = document.createElement("div");
                div.classList = 'seat-row';
                seatDIV.append(div);
                for(var j=1;j<this.data.seatCol;j++){
                    var btnValue = row+'-'+j
                 
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
                    seatBtn.addEventListener('click',function(e){
                        console.log(e.target.value)
                        const countPeople = document.querySelector('.p-people-count');
                        const price = document.querySelector('.p-price-won');
                        const seatInfo = document.querySelector('.hidden-seat-info');
                        const seat = document.querySelector('.p-seat');

                        if(seatBtn.classList.contains('clicked')) {
                            seatBtn.classList.remove('clicked');
                            seatBtn.style ="margin:10px; border : 1px solid gray; background :transparent"
                            
                            count--;
                            // 리스트에서 value 찾아서 빼기
                            var index = clickSeat.indexOf(e.target.value)
                            clickSeat.splice(index,1)
                            
                        }else {
                            seatBtn.classList.add('clicked');
                            seatBtn.style ="margin:10px; border : 1px solid gray; background : skyblue;"
                            count++;
                            
                            //버튼 클릭 시 리스트에 e.target.value 추가
                            clickSeat.push(e.target.value)
                        }
                        countPeople.innerHTML = count+' 명';
                        price.innerHTML = (count * moviePrice)+' 원';

                        seat.innerHTML = '좌석 : '+clickSeat
                        seatInfo.value = clickSeat;
                    })
                    div.append(seatBtn);
                    
                }
            }
        },        
        goTicketing($event) {
            const seat = document.querySelector('.hidden-seat-info')
            const countPeople = document.querySelector('.p-people-count');
            const price = document.querySelector('.p-price-won');
            const timeBtn = document.querySelectorAll('.btn-start-time');
            const movieNo = $event.target.value;
            for(var i=0;i<timeBtn.length;i++){
                if(timeBtn[i].value === movieNo) {
                    this.sentMovieInfo.startTime = timeBtn[i].textContent;
                    break;
                }
            }

            this.sentMovieInfo.movieNo = movieNo;
            this.sentMovieInfo.title = this.data.title;
            this.sentMovieInfo.room = this.data.room;
            this.sentMovieInfo.price = price.textContent;
            this.sentMovieInfo.countPeople = countPeople.textContent;
            
            this.selectedSeat = seat.value;
            this.ticketing = true;
            // this.$router.push({path: "/ticketing",name:'ticketing', params: { seat : seat.value, movieNo : movieNo}})

        }
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

    .movie-start-time, .current-seat-status {
        margin-left : 50px;
        margin-top : 50px;
    }
    .movie-start-time select {
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
    .btn-start-time {
        border : 1px solid gray;
    }
</style>