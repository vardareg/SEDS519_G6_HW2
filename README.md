# Weekly Course Schedule Generator

This Java application generates a **weekly university course schedule** by reading an HTML template and multiple lecturer preference files, filling courses into their preferred time slots, and saving the updated schedule as HTML.

**Design patterns:** Strategy, Decorator, Factory
**Input/Output:** Reads from `inputs/`, writes to `output/`

---

## Features

* Reads a base weekly schedule table from `inputs/output.html`.
* Reads lecturer preferences from `.txt` files (one per lecturer) in `inputs/`.
* Fills in the requested slots if empty, showing each entry in red.
* If a slot is occupied, prints a **red warning** in the terminal and skips the assignment (does not overwrite).
* Handles invalid lines and missing time slots gracefully with **clear red warnings**.
* Uses Strategy, Decorator, and Factory patterns as required.
* Outputs the final HTML schedule to `output/output.html`.

---

## Folder Structure

```
SEDS519_G6_HW2/
├── inputs/
│   ├── output.html          # HTML schedule template (input)
│   ├── Lecturer1.txt        # Lecturer preference files
│   ├── Lecturer2.txt
│   └── ...
├── output/
│   └── output.html          # Final schedule (output)
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── iyte/
│                   └── seds519/
│                       ├── app/
│                       ├── decorator/
│                       ├── factory/
│                       ├── model/
│                       ├── schedule/
│                       ├── strategy/
│                       └── util/
├── pom.xml                  # Maven build file
├── README.md
└── target/                  # Build artifacts (ignore)
```

---

## Input Formats

### 1. `inputs/output.html`

A standard HTML table representing the weekly schedule.
**Example:**

```html
<table border="1">
  <tr>
    <th>Time Slot</th><th>Pazartesi</th><th>Sali</th><th>Carsamba</th><th>Persembe</th><th>Cuma</th>
  </tr>
  <tr>
    <td>08:45</td><td></td><td></td><td></td><td></td><td></td>
  </tr>
  <!-- ... more rows ... -->
</table>
```

### 2. Lecturer Preference Files

Each lecturer has a file: `LecturerName.txt`
**Each line (4 fields, separated by `;`):**

```
CourseCode; Day (Turkish); Time (HH:mm); Lecture/Room/Group
```

**Example (`inputs/AAksoy.txt`):**

```
MATH142; Cuma; 10:45; D2
MATH242; Persembe; 15:15; D3
PHYS101; Pazartesi; 08:45; D1
```

---

## How to Build & Run

1. **Prepare your inputs:**
   Place `output.html` and all `.txt` files in the `inputs/` folder.

2. **Build the project:**

   ```sh
   mvn clean package
   ```

3. **Run the scheduler:**

   ```sh
   mvn exec:java
   ```

   * Or run the `Main` class via your IDE.

4. **Check output:**
   The updated schedule will be in `output/output.html`.

---

## Design Patterns Used

* **Strategy:** Selects different time slot assignment logic.
* **Decorator:** Dynamically adds validation and logging to the scheduling process.
* **Factory:** Creates scheduler objects (different schedule types if needed).

---

## Error Handling

* **All warnings and errors print in red** in the terminal.
* If a time slot is already filled, a warning shows **who occupies it**.
* Invalid lines and unrecognized days are also reported in red.
* The program continues scheduling after warnings.

---

## Example Output

* Scheduled courses appear in **red** in the HTML table.
* Terminal shows all actions, warnings, and errors, with conflicts and issues in **red**.

---

## Notes

* Do **not** modify anything in `target/`.
* Only place input/output files in their respective folders (`inputs/`, `output/`).

