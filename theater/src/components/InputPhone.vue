<template>
  <transition name="modal">
    <div class="modal-mask" @click.self="$emit('close')">
      <div class="modal-wrapper" @click.self="$emit('close')" >
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              
            </slot>
          </div>

          <div class="modal-body">
            
            <input class="input-phone" type='text' v-model='phoneFirst' placeholder="ex) 010" maxlength="3"/> -
            <input class="input-phone" type='text' v-model='phoneMiddle' placeholder="ex) 0000" maxlength="4"/> -
            <input class="input-phone" type='text' v-model='phoneLast' placeholder="ex) 1111" maxlength="4"/>
          </div>

          <div class="modal-footer">
            <slot name="footer">
         
              <!-- <button class="modal-default-button" @click="$emit('close')"> -->
                <!-- 하위 모달에서 상위 모달 끄기 적용 필요 -->
              <button class="modal-default-button btn btn-primary" @click="getPhone()">
                확인
              </button> 
            </slot>
          </div>
        </div>
        <TicketingCheck v-if="ticketCheck" @close="ticketCheck = false" :phone='phone'/>
      </div>
    </div>
  </transition>


</template>

<script>
import TicketingCheck from '@/components/TicketingCheck.vue'
export default {
  name: 'InputPhone',
  components : {
    TicketingCheck,
  },
  data () {
    return {
      phoneFirst :'',
      phoneMiddle : '',
      phoneLast : '',
      phone: '',
      ticketCheck : false,
      showModal : true
    }
  },
  methods : {
    getPhone() {
      if(this.phoneFirst !== '010' || this.phoneFirst.length < 3 || this.phoneMiddle.length<4||this.phoneLast.length<4
            || isNaN(this.phoneMiddle) || isNaN(this.phoneLast)){
        alert("올바른 핸드폰 번호를 입력해주세요.")
        return false;
      }
      this.showModal=false
      this.ticketCheck=true
      this.phone = this.phoneFirst+this.phoneMiddle+this.phoneLast
    },
  }

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

.modal-container {
  width: 500px;
 

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
.input-phone {
  width:125px;
  vertical-align: middle;
}
</style>