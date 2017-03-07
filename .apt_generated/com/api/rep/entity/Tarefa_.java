package com.api.rep.entity;

import com.api.rep.entity.Coleta;
import com.api.rep.entity.Configuracao;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.Rep;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T17:30:03")
@StaticMetamodel(Tarefa.class)
public class Tarefa_ { 

    public static volatile SingularAttribute<Tarefa, Integer> nsu;
    public static volatile SingularAttribute<Tarefa, Coleta> coletaId;
    public static volatile SingularAttribute<Tarefa, String> cpf;
    public static volatile SingularAttribute<Tarefa, Empregador> empregadorId;
    public static volatile SingularAttribute<Tarefa, Integer> tipoOperacao;
    public static volatile SingularAttribute<Tarefa, Integer> tipoTarefa;
    public static volatile SingularAttribute<Tarefa, Configuracao> configuracaoId;
    public static volatile SingularAttribute<Tarefa, Rep> repId;
    public static volatile SingularAttribute<Tarefa, Empregado> empregadoId;

}