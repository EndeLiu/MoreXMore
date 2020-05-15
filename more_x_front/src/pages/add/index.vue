<template>
  <div class="edit-container">
    <div class="edit-items">
      <div class="edit-item-val-type" @click="callCalculator" @touchstart="touchStart" @touchend="touchEnd">
        <div :class="accountItem.type === 0 ? 'edit-item-type out':'edit-item-type in'">{{accountItem.type === 0 ? '支出':'收入'}}</div>
        <div :class="accountItem.type === 0 ? 'edit-item-value out':'edit-item-value in'">{{accountItem.value}}</div>
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
        accountItem: {
          type: 0,
          value : '0.00'
        },
        touchStartX:null
      }
    },
    methods: {
      getCalRes (res){
        this.value = res
      },
      callCalculator() {
        this.showCalculator = !this.showCalculator
      },
      touchStart (e) {
        this.touchStartX = e.pageX
      },
      touchEnd(e){
        if(Math.abs(e.mp.changedTouches[0].pageX - this.touchStartX) > 50){
          this.accountItem.type = 1 - this.accountItem.type
        }
      }

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
  display: flex;
  .edit-item-type {
    font-size: 12px;
    margin-left: 10px;
  }
  .edit-item-value {
    text-align: center;
    translate: -8%;
    flex: 1;
  }

}
.in {
  color: crimson;
}
.out {
  color: green;
}
</style>
