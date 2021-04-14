<template>
  <div class="calculator">
      <div class="btn result">{{rst||0}}</div>
      <div class="btn" v-on:click="init()">AC</div>
      <div class="btn" v-on:click="changePlusMinus()">+/-</div>
      <div class="btn" v-on:click="deleteOne()">DEL</div>
      <div class="btn opertaion" v-on:click="operation('/')">/</div>
      <div class="btn" v-on:click="getNumber('7')">7</div>
      <div class="btn" v-on:click="getNumber('6')">6</div>
      <div class="btn" v-on:click="getNumber('5')">5</div>
      <div class="btn operation" v-on:click="operation('*')">x</div>
      <div class="btn" v-on:click="getNumber('4')" >4</div>
      <div class="btn" v-on:click="getNumber('5')">5</div>
      <div class="btn" v-on:click="getNumber('6')">6</div>
      <div class="btn operation" v-on:click="operation('-')">-</div>
      <div class="btn" v-on:click="getNumber('1')">1</div>
      <div class="btn" v-on:click="getNumber('2')">2</div>
      <div class="btn" v-on:click="getNumber('3')">3</div>
      <div class="btn operation" v-on:click="operation('+')">+</div>
      <div class="btn zero" v-on:click="getNumber('0')">0</div>
      <div class="btn" v-on:click="getDot()">.</div>
      <div class="btn" v-on:click="result()">=</div>
      <span>{{exp}}</span>
  </div>

</template>

<script>
export default {
    data() {
        return {
            num : "",
            rst : "",
            exp : "0",
            flag : false,
        }
    },
    methods : {
        //숫자 입력
        getNumber(num) {

            //수식의 첫 자리가 0인경우 이어붙히지 않게하기 위해서
            if(this.exp.length == 1 && this.exp === "0"){
                this.exp = "";
            }

            //결과 입력창이 0인 경우 숫자가 바뀌도록
            if(this.rst.charAt(0) === '0' && this.rst.length == 1) {
                this.rst = "";
                this.exp = this.exp.substr(0, this.exp.length-1);

            }

            //수식에 숫자 입력
            this.exp += num;
            
            if(this.flag){
                this.rst = "";
                this.flag = false;
            }
            this.rst += num;
            
        },
        //수식과 입력창 한개 삭제
        deleteOne() {
            if(this.rst.length > 0)
                this.exp = this.exp.substr(0,this.exp.length - 1);
            this.rst = this.rst.substr(0,this.rst.length - 1);
            
        },

        //초기화 
        init() {
            this.exp = "";
            this.rst = "";
        },

        //부호 변환(입력값, 수식값)
        changePlusMinus() {
            var cutLength = this.rst.length;
            this.rst = (this.rst > 0) ? "-" + this.rst :  this.rst.substr(1,this.rst.length - 1);
            this.exp = this.exp.substr(0,this.exp.length-cutLength) + this.rst;
        },

        //연산자 붙히기
        operation(opt) {
            if(isNaN(this.exp.charAt(this.exp.length-1))) {
                this.exp = this.exp.substr(0,this.exp.length-1)+opt;
               
            }else {
                this.exp+=opt;
            }
            this.flag = true;
        },

        //소수점 붙히기
        getDot() {
            if(this.rst.indexOf('.')===-1){
                this.rst += '.';
                this.exp += '.';
            }
        },

        //결과 출력
        result() {
            //결과 클릭 후 다시 연산하는 경우를 위해 String으로 변환
            this.rst = eval(this.exp).toString();
            this.exp = this.rst.toString();
            this.flag = true;
        },
    }
}
</script>

<style>
    .calculator {
        display : grid;
        grid-auto-rows: minmax(50px, auto);
        grid-template-columns: repeat(4, 1fr);
    }
    
    div {
        border : 1px solid #333;
    
    }

    .result {
        grid-column: 1/5;
        background-color : red;
    }

    .zero {
        grid-column : 1 /3;
    }

</style>