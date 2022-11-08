<template>
    <div>
        <div>
            <Top></Top>
        </div>

        <div style="margin:50px"></div>

        <div class="doc-container" id="blogindexarticle" style="background: url('http://userupfile.oss-cn-hangzhou.aliyuncs.com/public/root/IMG/WelcomeImage/Welcome.jpg');" v-cloak >
            <div class="container-fixed"  style="width: 68%;">
                <div class="col-content"  style="width: 100%;">
                    <div class="inner">
                        <article class="article-list bloglist" id="LAY_bloglist">              
                            <div>
                                <section class="article-item zoomIn article" style="border-radius:5px">
                     
                                    <h5 class="title">
                                        <a href="#">哈尔滨广厦学院题库</a>
                                    </h5>
                              
                                    <div class="content">
                                        <div class="el-table el-table--fit el-table--enable-row-hover el-table--enable-row-transition" style="width: 100%;">
                                            <div class="hidden-columns">
                                                <div></div><div></div> <div></div>
                                            </div>

                                            <div class="el-table__header-wrapper">
                                                <table cellspacing="0" cellpadding="0" border="0" class="el-table__header"
                                                style="width: 100%;">
                                                    <colgroup>
                                                        <col name="el-table_4_column_10" width="180">
                                                        <col name="el-table_4_column_11" width="180">
                                                        <col name="el-table_4_column_12" width="180">
                                                        <col name="el-table_4_column_13" width="180">
                                                    </colgroup>
                                                    <thead class="">
                                                        <tr class="">
                                                            <th colspan="1" rowspan="1" class="el-table_4_column_10     is-leaf el-table__cell">
                                                                <div class="cell">
                                                                    <b>编号</b>
                                                                </div>
                                                            </th>
                                                            <th colspan="1" rowspan="1" class="el-table_4_column_11     is-leaf el-table__cell">
                                                                <div class="cell">
                                                                    标题
                                                                </div>
                                                            </th>
                                                            <th colspan="1" rowspan="1" class="el-table_4_column_12     is-leaf el-table__cell">
                                                                <div class="cell">
                                                                    通过率
                                                                </div>
                                                            </th>
                                                            <th colspan="1" rowspan="1" class="el-table_4_column_13     is-leaf el-table__cell">
                                                                <div class="cell">
                                                                     难度
                                                                </div>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div class="el-table__body-wrapper is-scrolling-none">
                                                <table cellspacing="0" cellpadding="0" border="0" class="el-table__body"
                                                style="width: 100%;">
                                                    <colgroup>
                                                        <col name="el-table_4_column_10" width="180">
                                                            <col name="el-table_4_column_11" width="180">
                                                                <col name="el-table_4_column_12" width="180">
                                                                <col name="el-table_4_column_13" width="180">
                                                    </colgroup>
                                                    <tbody>
                                                        <tr class="el-table__row">
                                                            <td rowspan="1" colspan="1" class="el-table_4_column_10   el-table__cell">
                                                                <div class="cell">
                                                                    P10001
                                                                </div>
                                                            </td>
                                                            <td rowspan="1" colspan="1" class="el-table_4_column_11   el-table__cell">
                                                                <div class="cell" @click="toQuestion()">
                                                                    <a herf="#">张义嘉老师的数学题</a>
                                                                </div>
                                                            </td>
                                                            <td rowspan="1" colspan="1" class="el-table_4_column_12   el-table__cell">
                                                                <div class="cell">
                                                                    100%
                                                                </div>
                                                            </td>
                                                            <td rowspan="1" colspan="1" class="el-table_4_column_13   el-table__cell">
                                                                <div class="cell">
                                                                    简单
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="el-table__column-resize-proxy" style="display: none;">
                                            </div>
                                        </div>
                                      
                                    </div>

                                    <aside class="f-oh footer">
                                        <div class="f-fl tags">
                                            <i class="el-icon-user-solid"></i>
                                            <a class="tag" href="#">张义嘉老师 & TLM团队</a>
                                        </div>
                                        <div class="f-fr"> 
                                            <span class="read">
                                                <i class="fa fa-eye fs-16"></i>
                                                <i class="num">By 张义嘉老师 & TLM团队</i>
                                            </span>
                                        </div>
                                    </aside>
                                </section>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <Foot></Foot>
        </div>
    </div>
  </template>
  
  
  <script>
    import Foot from '../frame/Foot.vue'; 
    import Top from '../frame/Top.vue';
    import global from "../../../../static/entry.js";
    import {synRequestGet} from "../../../../static/request.js";
    export default {
       name: "anwserQuestions",     //对外开放打包
       components: { Foot,Top },
       data() {
           return{
               
           }
       },
  
       mounted(){
           
       },
  
       methods: {
           
            toQuestion(){
               
               this.$router.push('/question/AnwserQuestion');
            }
       }
   }
</script> 