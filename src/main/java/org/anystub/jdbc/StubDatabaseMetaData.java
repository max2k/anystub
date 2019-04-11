package org.anystub.jdbc;

import org.anystub.Base;
import org.anystub.Supplier;

import java.sql.*;

// StubDatabaseMetaData is unique object in current stub
// if the field is not defined it gets real object
// each call on real object updates the records in stubfile

public class StubDatabaseMetaData implements DatabaseMetaData {
    final private StubConnection stubConnection;
    private DatabaseMetaData realDatabaseMetaData = null;

    public StubDatabaseMetaData(StubConnection stubConnection) throws SQLException {

        this.stubConnection = stubConnection;
        stubConnection.add(() -> {
            realDatabaseMetaData = stubConnection.getRealConnection().getMetaData();
        });
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.allProceduresAreCallable();
                              }
                          },
                        "DatabaseMetaData:allProceduresAreCallable");

    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.allTablesAreSelectable();
                              }
                          },
                        "DatabaseMetaData:allTablesAreSelectable");

    }

    @Override
    public String getURL() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getURL();
                             }
                         },
                        "DatabaseMetaData:getUrl");

    }

    @Override
    public String getUserName() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getUserName();
                             }
                         },
                        "DatabaseMetaData:getUserName");
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.isReadOnly();
                              }
                          },
                        "DatabaseMetaData:isReadOnly");

    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.nullsAreSortedHigh();
                              }
                          },
                        "DatabaseMetaData:nullsAreSortedHigh");

    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.nullsAreSortedLow();
                              }
                          },
                        "DatabaseMetaData:nullsAreSortedLow");

    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.nullsAreSortedAtStart();
                              }
                          },
                        "DatabaseMetaData:nullsAreSortedAtStart");

    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.nullsAreSortedAtEnd();
                              }
                          },
                        "DatabaseMetaData:nullsAreSortedAtEnd");

    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getDatabaseProductName();
                             }
                         },
                        "DatabaseMetaData:getDatabaseProductName");

    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getDatabaseProductVersion();
                             }
                         },
                        "DatabaseMetaData:getDatabaseProductVersion");
    }

    @Override
    public String getDriverName() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getDriverName();
                             }
                         },
                        "DatabaseMetaData:getDriverName");
    }

    @Override
    public String getDriverVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData.getDriverVersion();
                             }
                         },
                        "DatabaseMetaData:getDriverVersion");
    }

    @Override
    public int getDriverMajorVersion() {
        try {
            return stubConnection
                    .getStubDataSource()
                    .getBase()
                    .requestI(new Supplier<Integer, SQLException>() {
                                  @Override
                                  public Integer get() throws SQLException {
                                      stubConnection.runSql();
                                      return realDatabaseMetaData == null ? 0 :
                                              realDatabaseMetaData.getDriverMajorVersion();
                                  }
                              },
                            "DatabaseMetaData:getDriverMajorVersion");
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int getDriverMinorVersion() {
        try {
            return stubConnection
                    .getStubDataSource()
                    .getBase()
                    .requestI(new Supplier<Integer, SQLException>() {
                                  @Override
                                  public Integer get() throws SQLException {
                                      stubConnection.runSql();
                                      return realDatabaseMetaData == null ? 0 :
                                              realDatabaseMetaData.getDriverMinorVersion();
                                  }
                              },
                            "DatabaseMetaData:getDriverMinorVersion");
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.usesLocalFiles();
                              }
                          },
                        "DatabaseMetaData:usesLocalFiles");
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.usesLocalFilePerTable();
                              }
                          },
                        "DatabaseMetaData:usesLocalFilePerTable");
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:getDatabaseProductName");
    }

    @Override
    public String getSQLKeywords() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:getDatabaseProductName");
    }

    @Override
    public String getNumericFunctions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getStringFunctions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getSystemFunctions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsConvert() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsConvert(int i, int i1) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public String getSchemaTerm() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getProcedureTerm() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public String getCatalogTerm() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request(new Supplier<String, SQLException>() {
                             @Override
                             public String get() throws SQLException {
                                 stubConnection.runSql();
                                 return realDatabaseMetaData == null ? null :
                                         realDatabaseMetaData. ();
                             }
                         },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsUnion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxConnections() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxRowSize() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxStatements() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public ResultSet getProcedures(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getProcedureColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTables(String s, String s1, String s2, String[] strings) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getColumnPrivileges(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTablePrivileges(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getBestRowIdentifier(String s, String s1, String s2, int i, boolean b) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getVersionColumns(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPrimaryKeys(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getImportedKeys(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getExportedKeys(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getCrossReference(String s, String s1, String s2, String s3, String s4, String s5) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getIndexInfo(String s, String s1, String s2, boolean b, boolean b1) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsResultSetType(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsResultSetConcurrency(int i, int i1) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean ownUpdatesAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean ownDeletesAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean ownInsertsAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean othersUpdatesAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean othersDeletesAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean othersInsertsAreVisible(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean updatesAreDetected(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean deletesAreDetected(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean insertsAreDetected(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData.supportsBatchUpdates();
                              }
                          },
                        "DatabaseMetaData:supportsBatchUpdates");
    }

    @Override
    public ResultSet getUDTs(String s, String s1, String s2, int[] ints) throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public ResultSet getSuperTypes(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSuperTables(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getAttributes(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsResultSetHoldability(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public int getSQLStateType() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(new Supplier<Integer, SQLException>() {
                              @Override
                              public Integer get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? 0 :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSchemas(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getPseudoColumns(String s, String s1, String s2, String s3) throws SQLException {
        return null;
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(new Supplier<Boolean, SQLException>() {
                              @Override
                              public Boolean get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? false :
                                          realDatabaseMetaData. ();
                              }
                          },
                        "DatabaseMetaData:");
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
