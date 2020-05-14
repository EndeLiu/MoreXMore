<template>
  <div class="edit-container">
    <div class="edit-items">
      <div class="edit-item-val-type" @click="callCalculator">
        <div class="edit-item-type">收入</div>
        <div class="edit-item-value">{{value}}</div>
      </div>
      <div>分类【主分类和子分类】</div>
      <div>描述</div>
      <div>日期【日历表组件】</div>
      <div>支付方式</div>
    </div>

    <div  class="cal-root">
      <calculator @res="getCalRes" v-if="showCalculator"></calculator>
    </div>

  </div>
</template>

<script>
  import calculator from "../../components/add/calculator";
  export default {
    components: {
      calculator
    },
    data () {
      return {
        showCalculator: false,
        value : '0.00'
      }
    },
    methods: {
      getCalRes (res){
        this.value = res
      },
      callCalculator() {
        this.showCalculator = !this.showCalculator
      },

    }
  }
</script>

<style lang="scss">

.edit-container{
  padding: 10px;
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .edit-items {
    flex: 1 0 auto;
  }
  .cal-root {
    flex:0 0 auto;
    margin-bottom: 20px;
  }
}
.edit-item-val-type {
  margin-top: 10px;
  border: 1px solid white;
  border-radius: 4px;
  box-shadow: inset 1px 1px 1px 1px rgba(0,0,0,0.5);
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  font-weight: lighter;
  .edit-item-type {
    float: left;
    font-size: 12px;
    margin-left: 10px;
  }
  .edit-item-value {
    width: 100%;
    text-align: center;
    translate: -5%;
  }

}
</style>
