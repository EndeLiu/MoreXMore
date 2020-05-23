<template>
  <div>
    <picker-view :value="value" indicator-style="height: 50px;" indicator-class="picker-view-indicator" class="picker-view"  @change="bindChange">
      <picker-view-column class="picker-view-col" v-for="(col,colIndex) in cols" :key="colIndex">
        <view v-for="(item, index) in col" :key="index" >{{item.name}}</view>
      </picker-view-column>
    </picker-view>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        value:[0,0],
        colCount: 2,
        cols:[],
        type:'custom',
        closeFlag: true // true:不接受change事件
      }
    },


    methods: {
      open () {
        this.closeFlag = false
        this.$emit('res', this.value, this.type)
      },
      close () {
        this.closeFlag = true
      },

      dateMode (defaultDate) {
        this.type = 'date'

        let years = []
        let yearNum = []
        for (let i=2010;i<2050;i++) {
          yearNum.push(i)
          years.push({name:i})
        }

        let months = []
        let monthNum = []
        for (let i=0;i<12;i++) {
          monthNum.push(i+1)
          months.push({name:i+1})
        }

        let days = []
        let dayNum = []
        for (let i=0;i<31;i++) {
          dayNum.push(i+1)
          days.push({name:i+1})
        }
        this.colCount = 3
        this.cols = [years, months, days]

        if (defaultDate === null) {
          const today = new Date()
          this.value = [yearNum.indexOf(today.getFullYear()),monthNum.indexOf(today.getMonth()+1),dayNum.indexOf(today.getDate())]
        } else {
          this.value = [
            yearNum.indexOf(parseInt(defaultDate[0])),
            monthNum.indexOf(parseInt(defaultDate[1])),
            dayNum.indexOf(parseInt(defaultDate[2]))
          ]
        }
      },

      dateSelect (dateIndex) {
        const bigMonth = [1,3,5,7,8,10,12]
        const smallMonth = [4,6,9,11]
        let days = []
        for (let i=0;i<31;i++) {
          days.push({name:i+1})
        }
        // array.splice(indexOfItem, 1, newValue)
        if (smallMonth.indexOf(this.cols[1][dateIndex[1]].name) !== -1) {
          this.cols.splice(2, 1, days.slice(0,30))
        }
        else if (bigMonth.indexOf(this.cols[1][dateIndex[1]].name) !== -1) {
          this.cols.splice(2, 1, days)
        } else {
          if (this.cols[0][dateIndex[0]].name % 4 === 0) {
            this.cols.splice(2, 1, days.slice(0,29))
          } else {
            this.cols.splice(2, 1, days.slice(0,28))
          }
        }
      },

      bindChange(e) {
        if (!this.closeFlag) {
          const indexArray =  e.target.value
          // value 的 赋值逻辑
          for (let col=0;col<indexArray.length;col++) {
            if (this.value[col] !== e.target.value[col]) { // 当前列产生变化，其他后续列，重置为0索引,value结束
              this.value[col] = indexArray[col]
              for(let i=col+1;i<indexArray.length;i++) {
                this.value[i] = 0
              }
              break
            }
          }

          // value的提交逻辑
          if (this.type === 'date') {
            this.dateSelect(indexArray)
            this.$emit('res', this.value, this.type)
          }
          else if (this.type === 'custom') {
            this.$emit('res', this.value, this.type)
          }
        }
      }
    }
  }
</script>

<style lang="scss">
.picker-view-indicator {
  color: #322f3b;
  border-bottom: 1px solid rgba(0, 0, 0, 0.11);
}
.picker-view-col {
  background-color: white;
}
</style>
