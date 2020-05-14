<template>
  <div>
    <div class="calculator">
      <div class="cal-screen">
        <span>{{calInputContent}}</span>
      </div>

      <div class="cal-panel">
        <div class="cal-col" v-for="(col,colIndex) in cols" :key="colIndex">
          <div class="cal-btn" v-for="(item, index) in col" :key="index" @click="calInput(item)">
            <span>{{item}}</span>
          </div>
        </div>
        <div class="cal-col">
          <div class="cal-btn cal-ok" @click="submit">
            <span>ok</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        cols:[
          ['1','4','7','.'],
          ['2','5','8','0'],
          ['3','6','9','DEL'],
          ['+','-','*','/']
        ],
        cmd:'',
        calInputContent:'',
        calRes:0
      }
    },

    methods: {
      calInput (item) {
        if(this.isCmd(item)){ // 输入计算命令
          if(this.calInputContent === ''){
            return
          }
          // 如果当前最后一个字符为计算命令，则替换命令
          if(this.isCmd(this.calInputContent[this.calInputContent.length-1])){
            this.calInputContent = this.calInputContent.substring(0,this.calInputContent.length-1);
          } else {
            this.calInputContent = this.calculate(this.calInputContent) // 传入当前的表达式 或者值
          }
          this.cmd = item
          this.calInputContent += item
        }
        else if(item === 'DEL'){
          this.calInputContent = this.calInputContent.substring(0,this.calInputContent.length-1);
        } else {
          this.calInputContent += item
        }

      },

      isCmd (item) {
        return item === '+' || item === '-' || item === '*' || item === '/';
      },


      calculate(sourceExp) { // 传入 "1+3"
        const targetExp = sourceExp.split(/['+','\-','*','/']/) // ["1", "3"]
        if(targetExp.length > 1){  // "1+3" >1; "1" = 1
          if(targetExp.length === 2 && targetExp[1] === ''){
            return parseFloat(targetExp[0]).toFixed(2)
          }

          let [firstNum, secondNum] = targetExp.slice(-2)
          if(this.calInputContent[0] === '-'){
            firstNum = parseFloat(firstNum) * -1
          } else {
            firstNum = parseFloat(firstNum)
          }
          secondNum =  parseFloat(secondNum)  // 1,3

          let tempRes = 0
          if(secondNum === 0){
            return 'Inf'
          }

          switch(this.cmd) {
            case '+': tempRes = firstNum + secondNum;break;
            case '-': tempRes = firstNum - secondNum;break;
            case '*': tempRes = firstNum * secondNum;break;
            case '/': tempRes = firstNum / secondNum;break;
          }
          return  tempRes.toFixed(2) // 4
        }
        else if (targetExp.length === 1){ // 传入 "1"
          if(targetExp[0] === ''){
            return '0.00'
          } else {
            return parseFloat(targetExp).toFixed(2)
          }

        }
        else { //
          return '0.00'
        }

      },

      submit(){
        this.calRes = this.calculate(this.calInputContent)
        this.calInputContent = ''
        this.$emit('res',this.calRes)
      }
    }
  }
</script>

<style lang="scss">

.calculator {

  border: 1px solid white;
  border-radius: 4px;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
  .cal-screen {
    border: 1px solid white;
    border-radius: 4px;
    box-shadow: inset 1px 1px 1px 1px rgba(0,0,0,0.5);
    display: flex;
    justify-content: center;
    background-color: #e2e1e4;
    height: 30px;
    span {
      text-align: center;
      font-weight: bolder;
      font-size: 22px;
      color: #322f3b;
    }
  }

  .cal-panel {
    margin: 1px 5px;
    padding: 5px 2px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    .cal-col {
      width: 19%;
      margin:2px;
      display: flex;
      flex-direction: column;
      .cal-btn {
        border: 1px solid white;
        border-radius: 4px;
        color: #322f3b;
        display: flex;
        justify-content: center;
        font-size: 24px;
        font-weight: bolder;
        margin: 2px;
        background-color: white;
        box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
      }
      .cal-btn:active{
        background-color: #c6e6e8;
      }
      .cal-ok {
        flex-direction: column;
        height: 100%;
        border: 1px solid dodgerblue;
        background-color: dodgerblue;
        color: white;
        span {
          text-align: center;
        }

      }
    }


  }
}


</style>
