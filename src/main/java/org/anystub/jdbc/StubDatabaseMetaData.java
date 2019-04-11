package org.anystub.jdbc;

import org.anystub.Decoder;
import org.anystub.Encoder;
import org.anystub.Supplier;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import static java.util.Arrays.asList;

// StubDatabaseMetaData is unique object in current stub
// if the field is not defined it gets real object
// each call on real object updates the records in stubfile

public class StubDatabaseMetaData implements DatabaseMetaData {
    final private StubConnection stubConnection;
    private DatabaseMetaData realDatabaseMetaData = null;
    private Optional<DatabaseMetaData> rdbmd = null;

    public StubDatabaseMetaData(StubConnection stubConnection) throws SQLException {

        this.stubConnection = stubConnection;
        stubConnection.add(() -> {
            realDatabaseMetaData = stubConnection.getRealConnection().getMetaData();
            rdbmd = Optional.of(realDatabaseMetaData);
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
                                          realDatabaseMetaData.supportsMixedCaseIdentifiers();
                              }
                          },
                        "DatabaseMetaData:supportsMixedCaseIdentifiers");
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
                                          realDatabaseMetaData.storesUpperCaseIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesUpperCaseIdentifiers");
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
                                          realDatabaseMetaData.storesLowerCaseIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesLowerCaseIdentifiers");
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
                                          realDatabaseMetaData.storesMixedCaseIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesMixedCaseIdentifiers");
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
                                          realDatabaseMetaData.supportsMixedCaseQuotedIdentifiers();
                              }
                          },
                        "DatabaseMetaData:supportsMixedCaseQuotedIdentifiers");
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
                                          realDatabaseMetaData.storesUpperCaseQuotedIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesUpperCaseQuotedIdentifiers");
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
                                          realDatabaseMetaData.storesLowerCaseQuotedIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesLowerCaseQuotedIdentifiers");
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
                                          realDatabaseMetaData.storesMixedCaseQuotedIdentifiers();
                              }
                          },
                        "DatabaseMetaData:storesMixedCaseQuotedIdentifiers");
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
                                         realDatabaseMetaData.getIdentifierQuoteString();
                             }
                         },
                        "DatabaseMetaData:getIdentifierQuoteString");
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
                                         realDatabaseMetaData.getSQLKeywords();
                             }
                         },
                        "DatabaseMetaData:getSQLKeywords");
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
                                         realDatabaseMetaData.getNumericFunctions();
                             }
                         },
                        "DatabaseMetaData:getNumericFunctions");
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
                                         realDatabaseMetaData.getStringFunctions();
                             }
                         },
                        "DatabaseMetaData:getStringFunctions");
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
                                         realDatabaseMetaData.getSystemFunctions();
                             }
                         },
                        "DatabaseMetaData:getSystemFunctions");
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
                                         realDatabaseMetaData.getTimeDateFunctions();
                             }
                         },
                        "DatabaseMetaData:getTimeDateFunctions");
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
                                         realDatabaseMetaData.getSearchStringEscape();
                             }
                         },
                        "DatabaseMetaData:getSearchStringEscape");
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
                                         realDatabaseMetaData.getExtraNameCharacters();
                             }
                         },
                        "DatabaseMetaData:getExtraNameCharacters");
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
                                          realDatabaseMetaData.supportsAlterTableWithAddColumn();
                              }
                          },
                        "DatabaseMetaData:supportsAlterTableWithAddColumn");
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
                                          realDatabaseMetaData.supportsAlterTableWithDropColumn();
                              }
                          },
                        "DatabaseMetaData:supportsAlterTableWithDropColumn");
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
                                          realDatabaseMetaData.supportsColumnAliasing();
                              }
                          },
                        "DatabaseMetaData:supportsColumnAliasing");
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
                                          realDatabaseMetaData.nullPlusNonNullIsNull();
                              }
                          },
                        "DatabaseMetaData:nullPlusNonNullIsNull");
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
                                          realDatabaseMetaData.supportsConvert();
                              }
                          },
                        "DatabaseMetaData:supportsConvert");
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
                                          realDatabaseMetaData.supportsConvert(i, i1);
                              }
                          },
                        "DatabaseMetaData:supportsConvert", String.valueOf(i), String.valueOf(i1));
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
                                          realDatabaseMetaData.supportsTableCorrelationNames();
                              }
                          },
                        "DatabaseMetaData:supportsTableCorrelationNames");
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
                                          realDatabaseMetaData.supportsDifferentTableCorrelationNames();
                              }
                          },
                        "DatabaseMetaData:supportsDifferentTableCorrelationNames");
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
                                          realDatabaseMetaData.supportsExpressionsInOrderBy();
                              }
                          },
                        "DatabaseMetaData:supportsExpressionsInOrderBy");
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
                                          realDatabaseMetaData.supportsOrderByUnrelated();
                              }
                          },
                        "DatabaseMetaData:supportsOrderByUnrelated");
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
                                          realDatabaseMetaData.supportsGroupBy();
                              }
                          },
                        "DatabaseMetaData:supportsGroupBy");
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
                                          realDatabaseMetaData.supportsGroupByUnrelated();
                              }
                          },
                        "DatabaseMetaData:supportsGroupByUnrelated");
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
                                          realDatabaseMetaData.supportsGroupByBeyondSelect();
                              }
                          },
                        "DatabaseMetaData:supportsGroupByBeyondSelect");
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
                                          realDatabaseMetaData.supportsLikeEscapeClause();
                              }
                          },
                        "DatabaseMetaData:supportsLikeEscapeClause");
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
                                          realDatabaseMetaData.supportsMultipleResultSets();
                              }
                          },
                        "DatabaseMetaData:supportsMultipleResultSets");
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
                                          realDatabaseMetaData.supportsMultipleTransactions();
                              }
                          },
                        "DatabaseMetaData:supportsMultipleTransactions");
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
                                          realDatabaseMetaData.supportsNonNullableColumns();
                              }
                          },
                        "DatabaseMetaData:supportsNonNullableColumns");
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
                                          realDatabaseMetaData.supportsMinimumSQLGrammar();
                              }
                          },
                        "DatabaseMetaData:supportsMinimumSQLGrammar");
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
                                          realDatabaseMetaData.supportsCoreSQLGrammar();
                              }
                          },
                        "DatabaseMetaData:supportsCoreSQLGrammar");
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
                                          realDatabaseMetaData.supportsExtendedSQLGrammar();
                              }
                          },
                        "DatabaseMetaData:supportsExtendedSQLGrammar");
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
                                          realDatabaseMetaData.supportsANSI92EntryLevelSQL();
                              }
                          },
                        "DatabaseMetaData:supportsANSI92EntryLevelSQL");
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
                                          realDatabaseMetaData.supportsANSI92IntermediateSQL();
                              }
                          },
                        "DatabaseMetaData:supportsANSI92IntermediateSQL");
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
                                          realDatabaseMetaData.supportsANSI92FullSQL();
                              }
                          },
                        "DatabaseMetaData:supportsANSI92FullSQL");
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
                                          realDatabaseMetaData.supportsIntegrityEnhancementFacility();
                              }
                          },
                        "DatabaseMetaData:supportsIntegrityEnhancementFacility");
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
                                          realDatabaseMetaData.supportsOuterJoins();
                              }
                          },
                        "DatabaseMetaData:supportsOuterJoins");
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
                                          realDatabaseMetaData.supportsFullOuterJoins();
                              }
                          },
                        "DatabaseMetaData:supportsFullOuterJoins");
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
                                          realDatabaseMetaData.supportsLimitedOuterJoins();
                              }
                          },
                        "DatabaseMetaData:supportsLimitedOuterJoins");
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
                                         realDatabaseMetaData.getSchemaTerm();
                             }
                         },
                        "DatabaseMetaData:getSchemaTerm");
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
                                         realDatabaseMetaData.getProcedureTerm();
                             }
                         },
                        "DatabaseMetaData:getProcedureTerm");
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
                                         realDatabaseMetaData.getCatalogTerm();
                             }
                         },
                        "DatabaseMetaData:getCatalogTerm");
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
                                          realDatabaseMetaData.isCatalogAtStart();
                              }
                          },
                        "DatabaseMetaData:isCatalogAtStart");
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
                                         realDatabaseMetaData.getCatalogSeparator();
                             }
                         },
                        "DatabaseMetaData:getCatalogSeparator");
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
                                          realDatabaseMetaData.supportsSchemasInDataManipulation();
                              }
                          },
                        "DatabaseMetaData:supportsSchemasInDataManipulation");
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
                                          realDatabaseMetaData.supportsSchemasInProcedureCalls();
                              }
                          },
                        "DatabaseMetaData:supportsSchemasInProcedureCalls");
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
                                          realDatabaseMetaData.supportsSchemasInTableDefinitions();
                              }
                          },
                        "DatabaseMetaData:supportsSchemasInTableDefinitions");
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
                                          realDatabaseMetaData.supportsSchemasInIndexDefinitions();
                              }
                          },
                        "DatabaseMetaData:supportsSchemasInIndexDefinitions");
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
                                          realDatabaseMetaData.supportsSchemasInPrivilegeDefinitions();
                              }
                          },
                        "DatabaseMetaData:supportsSchemasInPrivilegeDefinitions");
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
                                          realDatabaseMetaData.supportsCatalogsInDataManipulation();
                              }
                          },
                        "DatabaseMetaData:supportsCatalogsInDataManipulation");
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsCatalogsInProcedureCalls();
                        },
                        "DatabaseMetaData:supportsCatalogsInProcedureCalls");
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsCatalogsInTableDefinitions();
                        },
                        "DatabaseMetaData:supportsCatalogsInTableDefinitions");
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsCatalogsInIndexDefinitions();
                        },
                        "DatabaseMetaData:supportsCatalogsInIndexDefinitions");
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsCatalogsInPrivilegeDefinitions();
                        },
                        "DatabaseMetaData:supportsCatalogsInPrivilegeDefinitions");
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsPositionedDelete();
                        },
                        "DatabaseMetaData:supportsPositionedDelete");
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
                                          realDatabaseMetaData.supportsPositionedUpdate();
                              }
                          },
                        "DatabaseMetaData:supportsPositionedUpdate");
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
                                          realDatabaseMetaData.supportsSelectForUpdate();
                              }
                          },
                        "DatabaseMetaData:supportsSelectForUpdate");
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
                                          realDatabaseMetaData.supportsStoredProcedures();
                              }
                          },
                        "DatabaseMetaData:supportsStoredProcedures");
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
                                          realDatabaseMetaData.supportsSubqueriesInComparisons();
                              }
                          },
                        "DatabaseMetaData:supportsSubqueriesInComparisons");
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
                                          realDatabaseMetaData.supportsSubqueriesInExists();
                              }
                          },
                        "DatabaseMetaData:supportsSubqueriesInExists");
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
                                          realDatabaseMetaData.supportsSubqueriesInIns();
                              }
                          },
                        "DatabaseMetaData:supportsSubqueriesInIns");
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
                                          realDatabaseMetaData.supportsSubqueriesInQuantifieds();
                              }
                          },
                        "DatabaseMetaData:supportsSubqueriesInQuantifieds");
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
                                          realDatabaseMetaData.supportsCorrelatedSubqueries();
                              }
                          },
                        "DatabaseMetaData:supportsCorrelatedSubqueries");
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
                                          realDatabaseMetaData.supportsUnion();
                              }
                          },
                        "DatabaseMetaData:supportsUnion");
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
                                          realDatabaseMetaData.supportsUnionAll();
                              }
                          },
                        "DatabaseMetaData:supportsUnionAll");
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
                                          realDatabaseMetaData.supportsOpenCursorsAcrossCommit();
                              }
                          },
                        "DatabaseMetaData:supportsOpenCursorsAcrossCommit");
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
                                          realDatabaseMetaData.supportsOpenCursorsAcrossRollback();
                              }
                          },
                        "DatabaseMetaData:supportsOpenCursorsAcrossRollback");
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
                                          realDatabaseMetaData.supportsOpenStatementsAcrossCommit();
                              }
                          },
                        "DatabaseMetaData:supportsOpenStatementsAcrossCommit");
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
                                          realDatabaseMetaData.supportsOpenStatementsAcrossRollback();
                              }
                          },
                        "DatabaseMetaData:supportsOpenStatementsAcrossRollback");
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
                                          realDatabaseMetaData.getMaxBinaryLiteralLength();
                              }
                          },
                        "DatabaseMetaData:getMaxBinaryLiteralLength");
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
                                          realDatabaseMetaData.getMaxCharLiteralLength();
                              }
                          },
                        "DatabaseMetaData:getMaxCharLiteralLength");
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
                                          realDatabaseMetaData.getMaxColumnNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnNameLength");
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
                                          realDatabaseMetaData.getMaxColumnsInGroupBy();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnsInGroupBy");
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
                                          realDatabaseMetaData.getMaxColumnsInIndex();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnsInIndex");
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
                                          realDatabaseMetaData.getMaxColumnsInOrderBy();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnsInOrderBy");
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
                                          realDatabaseMetaData.getMaxColumnsInSelect();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnsInSelect");
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
                                          realDatabaseMetaData.getMaxColumnsInTable();
                              }
                          },
                        "DatabaseMetaData:getMaxColumnsInTable");
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
                                          realDatabaseMetaData.getMaxConnections();
                              }
                          },
                        "DatabaseMetaData:getMaxConnections");
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
                                          realDatabaseMetaData.getMaxCursorNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxCursorNameLength");
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
                                          realDatabaseMetaData.getMaxIndexLength();
                              }
                          },
                        "DatabaseMetaData:getMaxIndexLength");
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
                                          realDatabaseMetaData.getMaxSchemaNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxSchemaNameLength");
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
                                          realDatabaseMetaData.getMaxProcedureNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxProcedureNameLength");
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
                                          realDatabaseMetaData.getMaxCatalogNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxCatalogNameLength");
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
                                          realDatabaseMetaData.getMaxRowSize();
                              }
                          },
                        "DatabaseMetaData:getMaxRowSize");
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
                                          realDatabaseMetaData.doesMaxRowSizeIncludeBlobs();
                              }
                          },
                        "DatabaseMetaData:doesMaxRowSizeIncludeBlobs");
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
                                          realDatabaseMetaData.getMaxStatementLength();
                              }
                          },
                        "DatabaseMetaData:getMaxStatementLength");
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
                                          realDatabaseMetaData.getMaxStatements();
                              }
                          },
                        "DatabaseMetaData:getMaxStatements");
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
                                          realDatabaseMetaData.getMaxTableNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxTableNameLength");
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
                                          realDatabaseMetaData.getMaxTablesInSelect();
                              }
                          },
                        "DatabaseMetaData:getMaxTablesInSelect");
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
                                          realDatabaseMetaData.getMaxUserNameLength();
                              }
                          },
                        "DatabaseMetaData:getMaxUserNameLength");
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
                                          realDatabaseMetaData.getDefaultTransactionIsolation();
                              }
                          },
                        "DatabaseMetaData:getDefaultTransactionIsolation");
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
                                          realDatabaseMetaData.supportsTransactions();
                              }
                          },
                        "DatabaseMetaData:supportsTransactions");
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsTransactionIsolationLevel(i);
                        },
                        "DatabaseMetaData:supportsTransactionIsolationLevel", String.valueOf(i));
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsDataDefinitionAndDataManipulationTransactions();
                        },
                        "DatabaseMetaData:supportsDataDefinitionAndDataManipulationTransactions");
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
                                          realDatabaseMetaData.supportsDataManipulationTransactionsOnly();
                              }
                          },
                        "DatabaseMetaData:supportsDataManipulationTransactionsOnly");
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
                                          realDatabaseMetaData.dataDefinitionCausesTransactionCommit();
                              }
                          },
                        "DatabaseMetaData:dataDefinitionCausesTransactionCommit");
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
                                          realDatabaseMetaData.dataDefinitionIgnoredInTransactions();
                              }
                          },
                        "DatabaseMetaData:dataDefinitionIgnoredInTransactions");
    }

    @Override
    public ResultSet getProcedures(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getProcedures(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getProcedures", s, s1, s2);
    }

    @Override
    public ResultSet getProcedureColumns(String s, String s1, String s2, String s3) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getProcedureColumns(s, s1, s2, s3);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getProcedureColumns", s, s1, s2, s3);
    }

    @Override
    public ResultSet getTables(String s, String s1, String s2, String[] strings) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getTables(s, s1, s2, strings);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getTables", s, s1, s2, Arrays.toString(strings));
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getSchemas();
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getSchemas");
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getCatalogs();
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getCatalogs");
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getTableTypes();
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getTableTypes");
    }

    @Override
    public ResultSet getColumns(String s, String s1, String s2, String s3) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getColumns(s, s1, s2, s3);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getColumns", s, s1, s2, s3);
    }

    @Override
    public ResultSet getColumnPrivileges(String s, String s1, String s2, String s3) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getColumnPrivileges(s, s1, s2, s3);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getColumnPrivileges", s, s1, s2, s3);
    }

    @Override
    public ResultSet getTablePrivileges(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getTablePrivileges(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getTablePrivileges", s, s1, s2);
    }

    @Override
    public ResultSet getBestRowIdentifier(String s, String s1, String s2, int i, boolean b) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getBestRowIdentifier(s, s1, s2, i, b);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getBestRowIdentifier", s, s1, s2, String.valueOf(i), String.valueOf(b));
    }

    @Override
    public ResultSet getVersionColumns(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getVersionColumns(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getVersionColumns", s, s1, s2);
    }

    @Override
    public ResultSet getPrimaryKeys(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getPrimaryKeys(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getPrimaryKeys", s, s1, s2);
    }

    @Override
    public ResultSet getImportedKeys(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getImportedKeys(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getImportedKeys", s, s1, s2);
    }

    @Override
    public ResultSet getExportedKeys(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getExportedKeys(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getExportedKeys", s, s1, s2);
    }

    @Override
    public ResultSet getCrossReference(String s, String s1, String s2, String s3, String s4, String s5) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getCrossReference(s, s1, s2, s3, s4, s5);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getCrossReference", s, s1, s2, s3, s4, s5);
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getTypeInfo();
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        "DatabaseMetaData:getTypeInfo");
    }

    @Override
    public ResultSet getIndexInfo(String s, String s1, String s2, boolean b, boolean b1) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getIndexInfo(s, s1, s2, b, b1);
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        "DatabaseMetaData:getIndexInfo", s, s1, s2, String.valueOf(b), String.valueOf(b1));
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
                                  return realDatabaseMetaData != null && realDatabaseMetaData.supportsResultSetType(i);
                              }
                          },
                        "DatabaseMetaData:supportsResultSetType", String.valueOf(i));
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
                                          realDatabaseMetaData.supportsResultSetConcurrency(i, i1);
                              }
                          },
                        "DatabaseMetaData:", String.valueOf(i), String.valueOf(i1));
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
                                          realDatabaseMetaData.ownUpdatesAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:ownUpdatesAreVisible", String.valueOf(i));
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
                                          realDatabaseMetaData.ownDeletesAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:ownDeletesAreVisible", String.valueOf(i));
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
                                          realDatabaseMetaData.ownInsertsAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:ownInsertsAreVisible", String.valueOf(i));
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
                                          realDatabaseMetaData.othersUpdatesAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:othersUpdatesAreVisible");
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
                                          realDatabaseMetaData.othersDeletesAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:othersDeletesAreVisible", String.valueOf(i));
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
                                          realDatabaseMetaData.othersInsertsAreVisible(i);
                              }
                          },
                        "DatabaseMetaData:othersInsertsAreVisible", String.valueOf(i));
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
                                          realDatabaseMetaData.updatesAreDetected(i);
                              }
                          },
                        "DatabaseMetaData:updatesAreDetected", String.valueOf(i));
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
                                          realDatabaseMetaData.deletesAreDetected(i);
                              }
                          },
                        "DatabaseMetaData:deletesAreDetected", String.valueOf(i));
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
                                          realDatabaseMetaData.insertsAreDetected(i);
                              }
                          },
                        "DatabaseMetaData:insertsAreDetected", String.valueOf(i));
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsBatchUpdates();
                        },
                        "DatabaseMetaData:supportsBatchUpdates");
    }

    @Override
    public ResultSet getUDTs(String s, String s1, String s2, int[] ints) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getUDTs(s, s1, s2, ints);
                              }
                          },
                        new DecoderResultSet(),
                        new EncoderResultSet(),
                        "DatabaseMetaData:getUDTs", s, s1, s2, Arrays.toString(ints));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return stubConnection;
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsSavepoints();
                        },
                        "DatabaseMetaData:supportsSavepoints");
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
                                          realDatabaseMetaData.supportsNamedParameters();
                              }
                          },
                        "DatabaseMetaData:supportsNamedParameters");
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsMultipleOpenResults();
                        },
                        "DatabaseMetaData:supportsMultipleOpenResults");
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
                                          realDatabaseMetaData.supportsGetGeneratedKeys();
                              }
                          },
                        "DatabaseMetaData:supportsGetGeneratedKeys");
    }

    @Override
    public ResultSet getSuperTypes(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getSuperTypes(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getSuperTypes", s, s1, s2);

    }

    @Override
    public ResultSet getSuperTables(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getSuperTables(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getSuperTables", s, s1, s2);
    }

    @Override
    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:", catalog, schemaPattern, typeNamePattern, attributeNamePattern);
    }

    @Override
    public boolean supportsResultSetHoldability(int i) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.supportsResultSetHoldability(i);
                        },
                        "DatabaseMetaData:supportsResultSetHoldability", String.valueOf(i));
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
                                          realDatabaseMetaData.getResultSetHoldability();
                              }
                          },
                        "DatabaseMetaData:getResultSetHoldability");
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
                                          realDatabaseMetaData.getDatabaseMajorVersion();
                              }
                          },
                        "DatabaseMetaData:getDatabaseMajorVersion");
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
                                          realDatabaseMetaData.getDatabaseMinorVersion();
                              }
                          },
                        "DatabaseMetaData:getDatabaseMinorVersion");
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
                                          realDatabaseMetaData.getJDBCMajorVersion();
                              }
                          },
                        "DatabaseMetaData:getJDBCMajorVersion");
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
                                          realDatabaseMetaData.getJDBCMinorVersion();
                              }
                          },
                        "DatabaseMetaData:getJDBCMinorVersion");
    }

    @Override
    public int getSQLStateType() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestI(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData == null ? 0 :
                                    realDatabaseMetaData.getSQLStateType();
                        },
                        "DatabaseMetaData:getSQLStateType");
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
                                          realDatabaseMetaData.locatorsUpdateCopy();
                              }
                          },
                        "DatabaseMetaData:locatorsUpdateCopy");
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
                                          realDatabaseMetaData.supportsStatementPooling();
                              }
                          },
                        "DatabaseMetaData:supportsStatementPooling");
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<RowIdLifetime, SQLException>() {
                              @Override
                              public RowIdLifetime get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getRowIdLifetime();
                              }
                          },
                        new Decoder<RowIdLifetime>() {
                            @Override
                            public RowIdLifetime decode(Iterable<String> values) {
                                if (values == null) {
                                    return RowIdLifetime.ROWID_UNSUPPORTED;
                                }
                                return RowIdLifetime.valueOf(values.iterator().next());
                            }
                        }, new Encoder<RowIdLifetime>() {
                            @Override
                            public Iterable<String> encode(RowIdLifetime resultSet) {
                                return asList(resultSet.name());
                            }
                        },
                        "DatabaseMetaData:RowIdLifetime");
    }

    @Override
    public ResultSet getSchemas(String s, String s1) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getSchemas(s, s1);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getSchemas", s, s1);
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
                                          realDatabaseMetaData.supportsStoredFunctionsUsingCallSyntax();
                              }
                          },
                        "DatabaseMetaData:supportsStoredFunctionsUsingCallSyntax");
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
                                          realDatabaseMetaData.autoCommitFailureClosesAllResultSets();
                              }
                          },
                        "DatabaseMetaData:autoCommitFailureClosesAllResultSets");
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getClientInfoProperties();
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getClientInfoProperties");
    }

    @Override
    public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getFunctions(s, s1, s2);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getFunctions", s, s1, s2);
    }

    @Override
    public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getFunctionColumns(s, s1, s2, s3);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getFunctionColumns", s, s1, s2, s3);
    }

    @Override
    public ResultSet getPseudoColumns(String s, String s1, String s2, String s3) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .request2(new Supplier<ResultSet, SQLException>() {
                              @Override
                              public ResultSet get() throws SQLException {
                                  stubConnection.runSql();
                                  return realDatabaseMetaData == null ? null
                                          : realDatabaseMetaData.getPseudoColumns(s, s1, s2, s3);
                              }
                          },
                        new Decoder<ResultSet>() {
                            @Override
                            public ResultSet decode(Iterable<String> values) {
                                return ResultSetUtil.decode(values);
                            }
                        }, new Encoder<ResultSet>() {
                            @Override
                            public Iterable<String> encode(ResultSet resultSet) {
                                return ResultSetUtil.encode(resultSet);
                            }
                        },
                        "DatabaseMetaData:getPseudoColumns", s1, s2, s3);
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.generatedKeyAlwaysReturned();
                        },
                        "DatabaseMetaData:generatedKeyAlwaysReturned");
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return stubConnection
                .getStubDataSource()
                .getBase()
                .requestB(() -> {
                            stubConnection.runSql();
                            return realDatabaseMetaData != null && realDatabaseMetaData.isWrapperFor(aClass);
                        },
                        "DatabaseMetaData:isWrapperFor", aClass.getCanonicalName());
    }
}
