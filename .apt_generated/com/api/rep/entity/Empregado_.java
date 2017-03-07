package com.api.rep.entity;

import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T16:33:27")
@StaticMetamodel(Empregado.class)
public class Empregado_ { 

    public static volatile CollectionAttribute<Empregado, Tarefa> tarefaCollection;
    public static volatile SingularAttribute<Empregado, String> empregadoCartaoBarras;
    public static volatile SingularAttribute<Empregado, String> empregadoCartaoTeclado;
    public static volatile SingularAttribute<Empregado, Integer> id;
    public static volatile SingularAttribute<Empregado, String> empregadoNome;
    public static volatile SingularAttribute<Empregado, Boolean> empregadoPossuiBio;
    public static volatile SingularAttribute<Empregado, String> empregadoCartaoProx;
    public static volatile SingularAttribute<Empregado, String> empregadoPis;
    public static volatile CollectionAttribute<Empregado, Rep> repCollection;
    public static volatile SingularAttribute<Empregado, String> empregadoNomeExibe;

}