package com.api.rep.entity;

import com.api.rep.entity.Tarefa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T16:33:27")
@StaticMetamodel(Coleta.class)
public class Coleta_ { 

    public static volatile CollectionAttribute<Coleta, Tarefa> tarefaCollection;
    public static volatile SingularAttribute<Coleta, String> coletaDataFim;
    public static volatile SingularAttribute<Coleta, String> coletaDataInicio;
    public static volatile SingularAttribute<Coleta, Integer> id;
    public static volatile SingularAttribute<Coleta, Integer> coletaNsrInicio;
    public static volatile SingularAttribute<Coleta, Integer> coletaNsrFim;

}