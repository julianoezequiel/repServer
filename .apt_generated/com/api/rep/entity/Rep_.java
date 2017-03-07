package com.api.rep.entity;

import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.Nsr;
import com.api.rep.entity.Tarefa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T16:33:27")
@StaticMetamodel(Rep.class)
public class Rep_ { 

    public static volatile SingularAttribute<Rep, String> numeroSerie;
    public static volatile CollectionAttribute<Rep, Tarefa> tarefaCollection;
    public static volatile SingularAttribute<Rep, String> chaveComunicacao;
    public static volatile CollectionAttribute<Rep, Nsr> nsrCollection;
    public static volatile SingularAttribute<Rep, Empregador> empregadorId;
    public static volatile SingularAttribute<Rep, Integer> id;
    public static volatile SingularAttribute<Rep, Empregado> empregadoId;

}