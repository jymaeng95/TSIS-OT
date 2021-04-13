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
      <div class="btn">.</div>
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
            top : -1,
       
            exp : "0",
            flag : false,
        }
    },
    methods : {
        getNumber(num) {
            if(this.exp.length == 1 && this.exp === "0"){
                this.exp = "";
            }
            this.exp += num;
            if(this.flag){
                this.rst = "";
                this.flag = false;
            }
            this.rst += num;
            
        },
        deleteOne() {
            if(this.rst.length > 0)
                this.exp = this.exp.substr(0,this.exp.length - 1);
            this.rst = this.rst.substr(0,this.rst.length - 1);
            
        },
        init() {
            this.exp = "";
            this.rst = "";
        },
        changePlusMinus() {
            var cutLength = this.rst.length;
            this.rst = (this.rst > 0) ? "-" + this.rst :  this.rst.substr(1,this.rst.length - 1);
            this.exp = this.exp.substr(0,this.exp.length-cutLength) + this.rst;


            // if(this.rst > 0) {
            //     this.exp = this.exp.substr(0,this.exp.length-this.rst.length);
            //     this.exp += "-"+this.rst;
            // }else {

            //     this.exp = this.exp.replace(this.exp.charAt(this.exp.length - this.rst.length),"");
            // }
            // this.exp += (this.rst > 0) ? "-" + this.rst : this.rst;
        },
        operation(opt) {
            if(isNaN(this.exp.charAt(this.exp.length-1))) {
                this.exp = this.exp.replace(this.exp.charAt(this.exp.length-1),opt);    
            }else {
                this.exp+=opt;

            }
            this.flag = true;
        },
        stackInit() {
            this.top =-1;
            this.stack = [];
        },
        isEmpty() {
            return this.stack.length == 0;
        },
        push(val) {
            this.stack[++this.top] = val;
        },
        pop() {
            if(this.top < 0){
                return -1;
            } else {
                var val = this.stack[this.top];
                this.stack = this.stack.slice(0, this.top);
                this.top--;
                return val;
            }
        },
        priority(val) {
            switch(val) {
                case '*':
                case '/':
                    return 1;
                case '+':
                case '-':
                    return -1;
            }
            return 2;
        },
        result() {
            var temp = "";
            var stack = [];
            var save = [];
            
            for(let i=0;i<this.exp.length; i++) {
                const char = this.exp.charAt(i);
                switch(char) {
                    case '+' : 
                    case '-' :
                    case '*' :
                    case '/' :
                        while(stack[stack.length-1]!=null && (this.priority(char) <= this.priority(stack[stack.length-1]))) {
                            temp+=stack.pop();
                            alert(temp);
                            if(isNaN(stack[stack.length-1])) {
                                save.push(temp);
                                temp = "";
                            }
                        }
                        stack.push(char);
                        break;
                    default :
                        temp+=char;
                        if(isNaN(this.exp.charAt(i+1)) || (i+1 == this.exp.length)){
                            save.push(temp);
                            temp = "";
                        }
                        break;
                }
            }
            temp = "";
            for(let i in save){
                temp+=save[i];

            }
            alert(temp);
        }
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