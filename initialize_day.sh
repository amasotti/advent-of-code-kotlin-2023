if [ ! -f "initialize_day.sh" ]; then
    echo "Please run this script from the root of the project"
    exit 1
fi

# Check that there is an argument with the day number
if [ $# -eq 0 ]; then
    echo "Please provide a day number as an argument"
    exit 1
fi

BLUE='\033[0;34m'
RESET_COLOR='\033[0m'

# Create dir
mkdir -p src/day$1
echo "${BLUE}Created directory src/day$1${RESET_COLOR}"

# Create challenge file
echo "${BLUE}Creating challenge description file${RESET_COLOR}"
#curl https://adventofcode.com/2023/day/$1 > src/day$1/Day$1_challenge.txt
touch src/day$1/Day$1_challenge.txt

# Create test file
echo "${BLUE}Creating test file${RESET_COLOR}"
touch src/day$1/Day$1_test.txt

# Create input file
echo "${BLUE}Creating input file${RESET_COLOR}"
curl https://adventofcode.com/2023/day/$1/input > src/day$1/Day$1.txt

# Create solution file
echo "${BLUE}Creating solution file${RESET_COLOR}"
touch src/day$1/Day$1.kt

# Create Utils file
echo "${BLUE}Creating Utils file${RESET_COLOR}"
touch src/day$1/Day$1Utils.kt

# Add kotlin files to git
echo "${BLUE}Adding files to git${RESET_COLOR}"
git add src/day$1/Day$1.kt
git add src/day$1/Day$1Utils.kt
