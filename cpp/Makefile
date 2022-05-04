FLATC := $(if $(FLATC),$(FLATC),$(HOME)/dhcpp/local/flatbuffers/bin/flatc)

TARGET_DIR := include

$(TARGET_DIR)/Barrage_generated.h: ../format/Barrage.fbs Makefile | include
	$(FLATC) --cpp -o $(TARGET_DIR) $<

$(TARGET_DIR):
	mkdir -p include
