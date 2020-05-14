<template>
  <div>
    <div class="cal-panel">
      <div class="cal-col" v-for="(col,colIndex) in cols" :key="colIndex">
        <div class="cal-btn" v-for="(item, index) in col" :key="index" @click="calInput(item)">
          <span>{{item}}</span>
        </div>
      </div>
      <div class="cal-col">
        <div class="cal-btn cal-ok" @click="calculate">
          <span>ok</span>
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
        calRes:0
      }
    },

    methods: {
      calInput (item) {
        console.log(item)
      },
      calculate(){
        //计算逻辑
        this.$emit('res',this.calRes)
      }
    }
  }
</script>

<style lang="scss">
.cal-panel {
  margin: 1px 5px;
  padding: 5px 2px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  border: 1px solid white;
  border-radius: 4px;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
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
</style>
