!function(){angular.module("webapp",["ngAnimate","ngCookies","ngTouch","ngSanitize","ngMessages","ngAria","ngResource","ui.router","toastr","rep"])}(),function(){"use strict";function t(){function t(){return e}var e=[{title:"AngularJS",url:"https://angularjs.org/",description:"HTML enhanced for web apps!",logo:"angular.png"},{title:"BrowserSync",url:"http://browsersync.io/",description:"Time-saving synchronised browser testing.",logo:"browsersync.png"},{title:"GulpJS",url:"http://gulpjs.com/",description:"The streaming build system.",logo:"gulp.png"},{title:"Jasmine",url:"http://jasmine.github.io/",description:"Behavior-Driven JavaScript.",logo:"jasmine.png"},{title:"Karma",url:"http://karma-runner.github.io/",description:"Spectacular Test Runner for JavaScript.",logo:"karma.png"},{title:"Protractor",url:"https://github.com/angular/protractor",description:"End to end test framework for AngularJS applications built on top of WebDriverJS.",logo:"protractor.png"},{title:"Material Design Lite",url:"http://www.getmdl.io/",description:"Add a Material Design look and feel to your websites.",logo:"material-design-lite.png"}];this.getTec=t}angular.module("webapp").service("webDevTec",t)}(),function(){"use strict";function t(){function t(t,e,a){var r=this;r.irParaRep=function(){a.cancel(),e.$state.go("repListar")},r.irParaHome=function(){e.$state.go("home")},r.relativeDate=t(r.creationDate).fromNow()}t.$inject=["moment","$rootScope","$timeout"];var e={restrict:"E",templateUrl:"app/components/navbar/navbar.html",scope:{creationDate:"="},controller:t,controllerAs:"vm",bindToController:!0};return e}angular.module("webapp").directive("acmeNavbar",t)}(),function(){"use strict";function t(t){function e(e,a,r,n){var l,o=t(a[0],{typeSpeed:40,deleteSpeed:40,pauseDelay:800,loop:!0,postfix:" "});a.addClass("acme-malarkey"),angular.forEach(e.extraValues,function(t){o.type(t).pause()["delete"]()}),l=e.$watch("vm.contributors",function(){angular.forEach(n.contributors,function(t){o.type(t.login).pause()["delete"]()})}),e.$on("$destroy",function(){l()})}function a(t,e){function a(){return r().then(function(){t.info("Activated Contributors View")})}function r(){return e.getContributors(10).then(function(){return n.contributors})}var n=this;n.contributors=[],a()}a.$inject=["$log","githubContributor"];var r={restrict:"E",scope:{extraValues:"="},template:"&nbsp;",link:e,controller:a,controllerAs:"vm"};return r}t.$inject=["malarkey"],angular.module("webapp").directive("acmeMalarkey",t)}(),function(){"use strict";function t(t,e){function a(a){function n(t){return t.data}function l(e){t.error("XHR Failed for getContributors.\n"+angular.toJson(e.data,!0))}return a||(a=30),e.get(r+"/contributors?per_page="+a).then(n)["catch"](l)}var r="https://api.github.com/repos/Swiip/generator-gulp-angular",n={apiHost:r,getContributors:a};return n}t.$inject=["$log","$http"],angular.module("webapp").factory("githubContributor",t)}(),function(){"use strict";function t(t,e,a){var r=this;r.listaRep=[],function n(){t.repStatus.query(function(t){r.listaRep=t,a.monitorar=e(n,1e3)},function(){a.monitorar=e(n,1e3)})}(),a.$on("$destroy",function(){e.cancel(a.monitorar)})}t.$inject=["RepService","$timeout","$rootScope"],angular.module("webapp").controller("MainController",t)}(),function(){"use strict";function t(t,e,a){return{request:function(r){if(a.debug("tokenInterceptor"),angular.isDefined(e.authToken)){var n=e.authToken;r.headers["X-Auth-Token"]=n}return r||t.when(r)}}}t.$inject=["$q","$rootScope","$log"],angular.module("webapp").factory("tokenInterceptor",t)}(),function(){"use strict";function t(t){t.interceptors.push("tokenInterceptor"),t.defaults.useXDomain=!0,t.defaults.headers.common["Access-Control-Allow-Origin"]="*",t.defaults.headers.common["Access-Control-Allow-Methods"]="POST, GET, OPTIONS, PUT, DELETE, HEAD, PUT",t.defaults.headers.common["Access-Control-Allow-Headers"]="X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept,*",t.defaults.headers.common["Access-Control-Max-Age"]="1728000"}t.$inject=["$httpProvider"],angular.module("webapp").config(t)}(),function(){"use strict";function t(t,e,a){e.$state=a,t.debug("app running")}t.$inject=["$log","$rootScope","$state"],angular.module("webapp").run(t)}(),function(){"use strict";function t(t,e){t.state("home",{url:"/",templateUrl:"app/main/main.html",controller:"MainController",controllerAs:"vm"}),e.otherwise("/")}t.$inject=["$stateProvider","$urlRouterProvider"],angular.module("webapp").config(t)}(),function(){"use strict";angular.module("webapp").constant("malarkey",malarkey).constant("moment",moment)}(),function(){"use strict";function t(t,e){t.debugEnabled(!0),e.allowHtml=!0,e.timeOut=3e3,e.positionClass="toast-top-right",e.preventDuplicates=!0,e.progressBar=!0}t.$inject=["$logProvider","toastrConfig"],angular.module("webapp").config(t)}(),function(){"use strict";function t(t,e){t.state("repListar",{url:"/listaRep",templateUrl:"app/components/rep/repLista.template.html",controller:"RepController",controllerAs:"vm"}).state("repAdicionar",{url:"/rep",templateUrl:"app/components/rep/rep.template.html",controller:"RepController",controllerAs:"vm"}).state("repEditar",{url:"/rep/:id",templateUrl:"app/components/rep/rep.template.html",controller:"RepController",controllerAs:"vm"}),e.otherwise("/")}function e(t,e){var a=this;a.listaRep=t.rep.query(),e.id&&t.rep.query({id:e.id},function(t){a.rep=t}),a.salvar=function(e){t.rep.update(e,function(t){e=t})}}t.$inject=["$stateProvider","$urlRouterProvider"],e.$inject=["RepService","$stateParams"],angular.module("rep",[]).config(t).controller("RepController",e)}(),function(){"use strict";function t(t){var e=t("rep",{id:"@id"},{update:{method:"PUT"}}),a=t("rep/monitoramento",{id:"@id"});return{rep:e,repStatus:a}}t.$inject=["$resource"],angular.module("rep").factory("RepService",t)}(),angular.module("webapp").run(["$templateCache",function(t){t.put("app/main/main.html",'<!-- <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">\r\n\r\n  <header class="mdl-layout__header">\r\n    <acme-navbar creation-date="main.creationDate"></acme-navbar>\r\n  </header>\r\n  <div class="mdl-layout__drawer">\r\n    <span class="mdl-layout-title">Rep Server</span>\r\n    <nav class="mdl-navigation">\r\n      <a class="mdl-navigation__link" data-ng-click="$state.go(\'rep\')" href="">Rep</a>\r\n      <a class="mdl-navigation__link" href="">Empregador</a>\r\n      <a class="mdl-navigation__link" href="">Empregados</a>\r\n      <a class="mdl-navigation__link" href="">ConfiguraÃ§Ãµes</a>\r\n    </nav>\r\n  </div> --><main class=mdl-layout__content><div class=page-content><div class="mdl-card mdl-shadow--2dp" style="width: 100%"><div class=mdl-card__title><h2 class=mdl-card__title-text>Monitoramento Inner Rep</h2></div><div class=mdl-card__supporting-text>Lista de equipamentos cadastrados</div><div style="padding: 20px"><table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"><thead><tr><th class=mdl-data-table__cell--non-numeric>NÚMERO DE SÉRIE</th><th class=mdl-data-table__cell--non-numeric>ÚLTIMO IP</th><th class=mdl-data-table__cell--non-numeric>CHAVE COMUNICAÇÃO</th><th class=mdl-data-table__cell--non-numeric>STATUS</th></tr></thead><tbody><tr data-ng-cloak data-ng-repeat="rep in vm.listaRep track by rep.id"><td class=mdl-data-table__cell--non-numeric>{{rep.numeroSerie}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.ultimoIp}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.chaveComunicacao}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.status}}</td></tr></tbody></table></div><div class="mdl-card__actions mdl-card--border"><a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Atualizar</a></div><!--<div class="mdl-card__menu">\r\n          <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">\r\n            <i class="material-icons">share</i>\r\n          </button>\r\n        </div>--></div><div class=mdl-grid><!--    <div class="mdl-cell mdl-cell--4-col" ng-repeat="awesomeThing in main.awesomeThings | orderBy:\'rank\'">\r\n          <div class="acme-card-square mdl-card mdl-shadow--2dp">\r\n            <div class="mdl-card__title">\r\n              <h2 class="mdl-card__title-text">{{ awesomeThing.title }}</h2>\r\n            </div>\r\n            <div class="mdl-card__media">\r\n              <img ng-src="assets/images/{{ awesomeThing.logo }}" alt="{{ awesomeThing.title }}">\r\n            </div>\r\n            <div class="mdl-card__supporting-text">\r\n              {{ awesomeThing.description }}\r\n            </div>\r\n            <div class="mdl-card__actions mdl-card--border">\r\n              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" ng-href="{{ awesomeThing.url }}">\r\n                Website\r\n              </a>\r\n            </div>\r\n          </div>\r\n        </div>--></div></div></main><!--  <footer class="mdl-mini-footer">\r\n    <div class="mdl-mini-footer__right-section">\r\n      <div class="mdl-logo">\r\n        Topdata - Inner Rep Plus\r\n      </div>\r\n    </div>\r\n  </footer> --> <!-- </div> -->'),t.put("app/components/navbar/navbar.html",'<div class=mdl-layout__header-row><!-- Title --> <span class="mdl-layout-title mdl-badge" data-ng-click=vm.irParaHome() style="cursor: pointer">Rep Server</span><div class=mdl-layout-spacer></div><!-- Add spacer, to align navigation to the right --><nav class="mdl-navigation mdl-layout--large-screen-only"><a class=mdl-navigation__link data-ng-click=vm.irParaRep() href="">Rep</a> <a class=mdl-navigation__link href=#>Empregador</a> <a class=mdl-navigation__link href=#>Empregados</a> <a class=mdl-navigation__link href=#>Configurações</a></nav><!-- Add spacer, to align navigation to the right --><!--<div class="mdl-layout-spacer"></div>--><!-- Navigation. We hide it in small screens. --><!-- <span class="mdl-navigation__link">Application was created {{ vm.relativeDate }}.</span>--></div>'),t.put("app/components/rep/rep.template.html",'<!-- <div class="mdl-layout mdl-js-layout mdl-color--grey-100" style="align-items: center;justify-content: center"> --><div class=mdl-layout__content style="padding: 10px; height: 100%"><div class="mdl-card mdl-shadow--6dp"><div class=mdl-card__title><h2 class=mdl-card__title-text>Cadastrar Inner Rep Plus</h2></div><div class=mdl-card__supporting-text><form action=#><div class="mdl-textfield mdl-js-textfield"><input class=mdl-textfield__input type=text id=numeroSerie><label class=mdl-textfield__label data-ng-model=vm.rep.numeroSerie for=numeroSerie>Número de Série</label></div><div class="mdl-textfield mdl-js-textfield"><input class=mdl-textfield__input type=password id=chaveCom><label class=mdl-textfield__label data-ng-model=chaveComunicacao for=chaveCom>Chave de Comunicação</label></div></form></div><div class="mdl-card__actions mdl-card--border"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" data-ng-click=vm.salvar()>Salvar</button></div></div></div><!-- </div> -->'),t.put("app/components/rep/repLista.template.html",'<!-- <div class="mdl-layout mdl-js-layout mdl-color--grey-100" style="align-items: center;justify-content: center"> --><div class=mdl-layout__content style="padding: 10px; height: 100%"><div class="mdl-card mdl-shadow--2dp" style="width: 100%"><div class="mdl-card__title mdl-color--primary mdl-color-text--white"><h2 class=mdl-card__title-text>Lista de Inner Rep Plus cadastrado</h2></div><div class=mdl-card__supporting-text><div style="padding: 20px"><table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"><thead><tr><th class=mdl-data-table__cell--non-numeric>NÚMERO DE SÉRIE</th><th class=mdl-data-table__cell--non-numeric>CHAVE COMUNICAÇÃO</th></tr></thead><tbody><tr data-ng-repeat="rep in vm.listaRep" data-ng-click="$state.go(\'repEditar\',{id:rep.id})"><td class=mdl-data-table__cell--non-numeric>{{rep.numeroSerie}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.chaveComunicacao}}</td></tr></tbody></table></div></div><div class="mdl-card__actions mdl-card--border"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Voltar</button></div></div></div><!-- </div> -->')}]);
//# sourceMappingURL=../maps/scripts/app-29bfe23301.js.map
