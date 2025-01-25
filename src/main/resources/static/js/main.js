const { createApp, ref } = Vue
createApp({
    template: `
<header></header>
<aside>
  <div>
    <select v-model="catalogSelected" @change="onChangeCatalogItem()">
      <option value=""/>
      <option v-for="catalogItem in catalog" :value="catalogItem.name">{{catalogItem.title}}</option>
    </select>
  </div>
<!--  <form method="GET" action="/api/0.0.1/state-transition" @submit="onSubmitFormStateTransit(this)">-->
  <div>
    <label v-for="workObjectItem in workObject" :for="workObjectItem.name">
<!--     name="woName"-->
      <input type="radio" :id="workObjectItem.name" :value="workObjectItem.name" @input="onInputWorkObjectItem">
      {{ workObjectItem.title }}
    </label>
  </div>
    <!--<label :for="workObjectItem.title + 'WorkObject'"></label>
    <input type="submit" value="submit">
    <input type="reset" value="reset" onClick="window.location.reload()">
  </form>-->
  <!--<form action="index.html" method="GET" v-for="(param, key, index) in integrationParams" :key="key">
    <input type="radio" name="name1" value="" v-model="param" :id="key">
    <label :for="key"></label></form>-->
</aside>
<main>
  <table v-for="table of data.content" border="1">
    <caption style="caption-side: top">{{table.caption}}</caption>
    <thead v-for="thead of table.head">
      <tr v-for="trh of thead.rowh">
        <th v-for="(th, i) in trh.column" :key="i" style="color: transparent"
            :ref="(el)=>{if(i%2){reference.stateSecEl.push(el)}}"
        >{{ i }}</th>
      </tr>
      <tr v-for="trd of thead.rowd" style="border-width: 1px">
        <td v-for="state in trd.state" :colspan="state.colSpan" style="text-align: center"
            :ref="(el)=>{reference.tdState.push({td:el,name:state.title})}"
        >{{ state.title }}</td>
      </tr>
    </thead>
    <tbody v-for="tbody of table.body">
      <tr v-for="trArrow in tbody.rowArrow">
        <td v-for="tdArrow in trArrow.cellArrow"
            :ref="(el)=>{if(tdArrow.hasOwnProperty('canvas')){let tempCanvas=reference.tempCanvas.pop();reference.tdArrow.push({td:el,toStateName:tempCanvas.to,fromStateName:tempCanvas.from})}}"
            :colspan="tdArrow.colSpan"
            :class="{cellBorderRight:tdArrow.borderRight===true,cellBorderLeft:tdArrow.borderLeft===true,firstCell:tdArrow.isFirstCell===true}" >
          <canvas v-if="tdArrow.canvas === true" class="arrow"
                  :ref="(el)=>{reference.tempCanvas.push({from:tdArrow.arrow[0],to:tdArrow.arrow[1]})}"
          >
          </canvas>
        </td>
      </tr>
      <tr v-for="trTitle in tbody.rowTitle">
        <td v-for="tdTitle in trTitle.cellTitle" :colspan="tdTitle.colSpan" :class="{cellBorderRight: tdTitle.borderRight === true, cellBorderLeft: tdTitle.borderLeft === true, firstCell: tdTitle.isFirstCell === true}">
          {{ tdTitle.title }}
        </td>
      </tr>
      <tr v-for="trDesc in tbody.rowDesc">
        <td v-for="tdDesc in trDesc.cellDesc" :colspan="tdDesc.colSpan" :class="{cellBorderRight: tdDesc.borderRight === true, cellBorderLeft: tdDesc.borderLeft === true, firstCell: tdDesc.isFirstCell === true}">
          <pre v-if="tdDesc.description">{{ tdDesc.description }}</pre>
        </td>
      </tr>
    </tbody>
  </table>
</main>
<footer></footer>`,
    data() {
        return {
            catalog: [],
            catalogSelected: null,
            workObject: [],
            reference: {
                tdState: [],
                tdArrow: [],
                tempCanvas: [],
                stateSecEl:[],
                rowEl:[],
            },
            data: {
                content: [/*{
            caption: "State transition mapping for work object : wSpace",
            head: [{
              rowh: [{
                column: [{}, {}, {}, {}, {}, {}, {}, {}],
              }],
              rowd: [
                {state: [{title: "NULL", colSpan: 2}, {title: "wDraft", colSpan: 2}, {title: "wActive", colSpan: 2}, {title: "wRetired", colSpan: 2}]} ]
            }],
            body: [
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {arrow:["NULL","wDraft"], colSpan: 2, borderLeft:true, borderRight:true, canvas:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {title: "wCreateDraft", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {description: "a\nb", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              },
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {arrow:["wDraft","wDraft"], colSpan: 2, canvas:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {title: "wAction", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {description: "ab\nbc", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              },
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {arrow:["wDraft","wRetired"], colSpan: 4, canvas:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {title: "wRetire", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {description: "abc\nxyz", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              }]
          }*/]
            },
            arrow: {
                start: null,
                end: null,
                action: ''
            },
            refTh: {
                empty: null,
                ref: []
            },
            arrElBegEnd: [],
            arrElBegEndCount: 1,
            arrElBegEndFillComplete: false
        }
    },
    created: function () {
        let request = new XMLHttpRequest();
        request.open("GET", "/api/0.0.1/catalog", true);
        request.onload = () => {
            if (this.catalog.length > 0) {
                this.catalog = []
            }
            // let responseCatalog = JSON.parse(request.response).catalog;
            for (let catalogItemResponse of JSON.parse(request.response).catalog) {
                this.catalog.push(catalogItemResponse)
            }
        }
        request.send()
    },
    computed: {
    },
    methods: {
        onChangeCatalogItem: function(value) {
            if (this.workObject !== null && this.workObject !== '') {
                let request = new XMLHttpRequest();
                request.open("GET", "/api/0.0.1/work-object/" + this.catalogSelected, true);
                request.onload = () => {
                    if (this.workObject.length > 0) {
                        this.workObject = []
                    }
                    let resp = JSON.parse(request.response);
                    if (resp.hasOwnProperty(this.catalogSelected)) {
                        for (let workObj of resp[this.catalogSelected]) {
                            this.workObject.push(workObj)
                        }
                    }
                    console.log("onChangeCatalogItem workObject " + this.workObject);
                    console.dir(this.workObject)
                    console.dir(this.catalog)
                }
                request.send()
            }

        },
        /*onSubmitFormStateTransit: function (formObject) {
            console.log("onSubmitFormStateTransit formObject" + formObject);
            console.dir(formObject);
            let request = new XMLHttpRequest();
            request.open(formObject.method, "/api/0.0.1/state-transition", true);
            request.onload = () => {
                let templ = respTempl("wPeople", request);
                if (templ != null) {
                    this.data.content.push(templ)
                }
            }
            request.send()
        },*/
        onInputWorkObjectItem: function(e) {
            let request = new XMLHttpRequest();
            console.log("onInputWorkObjectItem e.target" + e.target);
            console.dir(e.target);
            console.log("onInputWorkObjectItem target.value.value" + e.target.value);
            request.open("GET", "/api/0.0.1/state-transition/" + e.target.value, true);
            request.onload = () => {
                console.log("woResponse " + request.response);
                console.dir(request.response);
                /*let templ = respTempl("wPeople", request);
                if (templ != null) {
                    this.data.content.push(templ)
                }*/
            }
            request.send()
        },
        /*catalogItemSelect: function(el){
            let selectCatalog = this.$el.querySelector('#selectCatalog');
            let selectedOption = selectCatalog.options[selectCatalog.selectedIndex];
            console.log(selectedOption);
        },*/
        getWoObject: function() {
            let request = new XMLHttpRequest();
            request.open("GET", "/api/0.0.1/state-transition/wPeople", true);
            request.onload = () => {
                let templ = respTempl("wPeople", request);
                if (templ != null) {
                    this.data.content.push(templ)
                }
            }
            request.send()
        },
        onMountedDrawArrow(direct, canvas) {
            function drawPlainArrow(beginX, endX, rowY, canvas){
                let canvasContext = canvas.getContext("2d");
                canvasContext.fillStyle = 'steelblue';
                canvasContext.strokeStyle = 'steelblue';
                // draw line from start to end
                canvasContext.beginPath();
                canvasContext.moveTo(beginX, rowY);
                canvasContext.lineTo(endX, rowY);
                canvasContext.lineWidth = 2;
                canvasContext.stroke();
                // draw circle at beginning of line
                canvasContext.beginPath();
                canvasContext.arc(beginX, rowY, 4, 0, Math.PI * 2, true);
                canvasContext.fill();
                // draw pointer at end of line (needs rotation)
                canvasContext.beginPath();
                let angle = Math.atan2(0, endX - beginX);
                canvasContext.translate(endX, rowY);
                canvasContext.rotate(angle);
                canvasContext.moveTo(0, 0);
                canvasContext.lineTo(-10, -7);
                canvasContext.lineTo(-10, 7);
                canvasContext.lineTo(0, 0);
                canvasContext.fill();
            }
            drawPlainArrow(direct ? 0 : canvas.width / 2, direct ? canvas.width : 0, canvas.clientHeight / 2, canvas);
        }
    },
    /*async refreshStateTransit(event) {
      event.preventDefault();
      this.data.content.pop();
      this.data.content.push(await this.$http.get('/statetransit/v1/wSpace'));
      console.log("refreshStateTransit")
    },*/
    beforeMount() {
        /*      let request = new XMLHttpRequest();
              request.open("GET", "/api/0.0.1/state-transition/wPeople", true);
              request.onload = () => {
                /!*let buildTempl = function(obj) {
                  return null;
                }*!/
                // console.log(request.response);
                // let obj = JSON.parse(request.response);
                // console.dir(obj);
                let templ = respTempl("wPeople", request);
                if (templ != null) {
                  // console.dir(templ)
                  console.dir(this.data);
                  this.data.content.push(templ)
                }
              }
              request.send()*/
    },
    mounted() {
        console.log("Front start");
        console.log("catalogSelected " + this.catalogSelected);
        let content = JSON.stringify(this.data.content[0]);
        console.log(content);
        $(".firstCell").css({width: this.reference.tdState[0].td.offsetWidth/2});
        for (let arrow of this.reference.tdArrow) {
            let direct = true;
            if (arrow.toStateName === arrow.fromStateName) {
                direct = false
            }
            arrow.td.firstChild.height = arrow.td.parentElement.clientHeight;
            this.onMountedDrawArrow(direct, arrow.td.firstChild)
        }
    },
}).mount('#app')
