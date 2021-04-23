<template>
  <transition name="modal">
    <div class="modal-mask" @click.self="$emit('close')">
      <div class="modal-wrapper" @click.self="$emit('close')" >
        <div class="modal-container-ticketing">

          <div class="modal-header">
            <slot name="header">
              <h2>"{{phone}}" 님의 예매 내역</h2>
            </slot>
          </div>


          <div class="modal-body">
            <div v-for="(item,index) in movieInfo" :key="index" class="ticketing-info d-flex">
                <div class="movie-img">
                  <div class="card" style="width: 70px; margin:10px; margin-top:0px;">
                    <img v-if="item.room == 1" src="@/assets/clementain.jpg" class="card-img-top"  alt="...">
                    <img v-else-if="item.room == 2" src="@/assets/hero.jpg" class="card-img-top"  alt="...">
                    <img v-else src="@/assets/match.jpg" class="card-img-top"  alt="...">
                  </div>
                </div>
                <div class="ticket-info">
                  <div class="d-flex info-main">
                      <h4>{{movieInfo[index].room}}관</h4>
                      <h4>{{movieInfo[index].title}}</h4>
                      <h4>{{movieInfo[index].startTime}}</h4>
                      <h4>{{ticketInfo[index].countPeople}}명</h4>
                  </div>
                  <div class="d-flex info-main">
                      <h4>{{seatInfo[index]}}</h4>
                      <h4>{{movieInfo[index].price * ticketInfo[index].countPeople}}원</h4>
                      <button class="cancel-ticketing btn btn-primary">예매 취소</button>
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
import axios from 'axios'
export default {
    name : 'TicketingCheck',
    props : {
        phone : String,
    },
    data () {
    return {
      ticketInfo : [],
      movieInfo : [],
      seatInfo : [],
      seat :''
    }
  },
  mounted()  {

    const ticketURI = 'http://localhost:8080/ticketing';
    axios.get(`${ticketURI}/${this.phone}`)
    .then((result) => {
      // console.log(result)
      this.ticketInfo = result.data

      const movieURI = 'http://localhost:8080/movie/movieNo';
      for(let i=0;i<this.ticketInfo.length;i++){  
        axios.get(`${movieURI}/${this.ticketInfo[i].movieNo}`)
         .then((rst) => {
          console.log(rst)
          this.movieInfo.push(rst.data)
       
        })
        
      }

      const seatURI = 'http://localhost:8080/ticketing/seatStatus'
      for(let i=0;i<this.ticketInfo.length;i++){
        axios.get(`${seatURI}/${this.ticketInfo[i].ticketNo}`)
        .then((seatRst) => {
          // console.log(seatRst)
          this.seat ='';
          for(let j=0;j<seatRst.data.length;j++){
            this.seat += ""+seatRst.data[j].toString()+", "
          }
          this.seatInfo.push(this.seat.substring(0,this.seat.length-2))
        })
      }
    })
  },
}
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container-ticketing {
  width: 600px;
  height : 600px;

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

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
.info-main {
  margin-top : 15px;
}
.info-main h4, button {
  margin-left : 40px;
}

.ticketing-info {

  margin-top : 20px;
}
</style>