# Imports the ydata_profiling module and pandas
import pandas as pd
import ydata_profiling as pp

archivo_txt = '../src/delitos(jue. 09-03-2023).txt'
delimiter = '\t'
header = None
# Reads the file and creates a dataframe
df = pd.read_csv(archivo_txt, delimiter=delimiter, header=header, encoding='utf-8')

# Prints the columns of the dataframe
print(df.columns.values)
print("------------Primeros 5 registros------------", df.head(5))
print("------------Últimos 10 registros------------", df.tail(10))
print(df.describe())
# Creates a profile report
report = pp.ProfileReport(df)
output_file = "output.html"
report.to_file(output_file)
