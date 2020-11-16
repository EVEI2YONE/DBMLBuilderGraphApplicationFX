<h1>DataBaseMarkupLanguage Visualizer</h1>
<h3>Currently facing issues with actual interactiveness</h3>
<h3>But current syntax for constructing basic tables can be correctly parsed</h3>
<img src="images/interactive-DBML.png">

<h3>missing syntax for ENUM and REF</h3>
Correct First and Follow sets of grammar: http://hackingoff.com/compilers/predict-first-follow-set

CONTAINERS -> CONTAINER | CONTAINER CONTAINERS
CONTAINER -> TABLE | ENUM | REF | EPSILON
TABLE -> Table name { TABLE_CONTENT }
TABLE_CONTENT -> TABLE_ROW | TABLE_ROW TABLE_CONTENT | EPSILON
TABLE_ROW -> name TYPE | name TYPE pk | name ENUM_TYPE
ENUM -> Enum ENUM_TYPE { ENUM_CONTENT }
ENUM_CONTENT -> ENUM_ROW | ENUM_ROW ENUM_CONTENT
ENUM_ROW -> name [ note: name ]
REF -> ref
