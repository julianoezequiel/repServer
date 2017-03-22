angular.module("webapp").run(["$templateCache",function(a){a.put("app/main/main.html",'<main class=mdl-layout__content><div class=page-content><div class="mdl-card mdl-shadow--2dp" style="width: 100%"><div class=mdl-card__title><h2 class=mdl-card__title-text>Monitoramento Inner Rep</h2></div><div class=mdl-card__supporting-text>Lista de equipamentos cadastrados</div><div style="padding: 20px"><table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"><thead><tr><th class=mdl-data-table__cell--non-numeric>NÚMERO DE SÉRIE</th><th class=mdl-data-table__cell--non-numeric>ÚLTIMO IP</th><th class=mdl-data-table__cell--non-numeric>CHAVE	COMUNICAÇÃO</th><th class=mdl-data-table__cell--non-numeric>STATUS</th></tr></thead><tbody><tr data-ng-cloak data-ng-repeat="rep in vm.listaRep track by rep.id"><td class=mdl-data-table__cell--non-numeric>{{rep.numeroSerie}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.ultimoIp}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.chaveComunicacao}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.status}}</td></tr></tbody></table></div><div class="mdl-card__actions mdl-card--border"><a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" data-ng-click=vm.initStopMon()>{{vm.ismonitorando?"Parar":"Iniciar"}}</a></div></div></div></main>'),a.put("app/components/navbar/navbar.html",'<div class=mdl-layout__header-row><!-- Title --> <span class="mdl-layout-title mdl-badge" data-ng-click=vm.irParaHome() style="cursor: pointer">Rep Server</span><div class=mdl-layout-spacer></div><!-- Add spacer, to align navigation to the right --><nav class="mdl-navigation mdl-layout--large-screen-only"><a class=mdl-navigation__link data-ng-click=vm.irParaRep() href="">Rep</a> <a class=mdl-navigation__link href=#>Empregador</a> <a class=mdl-navigation__link href=#>Empregados</a> <a class=mdl-navigation__link href=#>Configurações</a></nav><!-- Add spacer, to align navigation to the right --><!--<div class="mdl-layout-spacer"></div>--><!-- Navigation. We hide it in small screens. --><!-- <span class="mdl-navigation__link">Application was created {{ vm.relativeDate }}.</span>--></div>'),a.put("app/components/rep/rep.template.html",'<div class=mdl-layout__content style="padding: 10px; height: 100%"><div class="mdl-card mdl-shadow--6dp"><div class=mdl-card__title><h2 class=mdl-card__title-text>Cadastrar Inner Rep Plus</h2></div><div class=mdl-card__supporting-text><form action=#><div class="mdl-textfield mdl-js-textfield"><input class=mdl-textfield__input type=text id=numeroSerie data-ng-model=vm.rep.numeroSerie><label class=mdl-textfield__label>Número de Série</label></div><div class="mdl-textfield mdl-js-textfield"><input class=mdl-textfield__input type=text id=chaveCom data-ng-model=vm.rep.chaveComunicacao><label class=mdl-textfield__label>Chave de Comunicação</label></div></form></div><div class="mdl-card__actions mdl-card--border"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" data-ng-click=vm.salvar(vm.rep)>Salvar</button></div></div></div>'),a.put("app/components/rep/repLista.template.html",'<div class=mdl-layout__content style="padding: 10px; height: 100%"><div class="mdl-card mdl-shadow--2dp" style="width: 100%"><div class=mdl-card__title><h2 class=mdl-card__title-text>Lista de Inner Rep Plus cadastrado</h2></div><div class=mdl-card__supporting-text><div style="padding: 20px"><table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"><thead><tr><th class=mdl-data-table__cell--non-numeric>NÚMERO DE SÉRIE</th><th class=mdl-data-table__cell--non-numeric>CHAVE COMUNICAÇÃO</th></tr></thead><tbody><tr data-ng-repeat="rep in vm.listaRep" data-ng-click="$state.go(\'repEditar\',{id:rep.id})"><td class=mdl-data-table__cell--non-numeric>{{rep.numeroSerie}}</td><td class=mdl-data-table__cell--non-numeric>{{rep.chaveComunicacao}}</td></tr></tbody></table></div></div><div class="mdl-card__actions mdl-card--border"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" data-ng-click="$state.go(\'home\')">Voltar</button></div></div></div>')}]);
//# sourceMappingURL=../maps/scripts/app-d7dcfa8e6d.js.map
